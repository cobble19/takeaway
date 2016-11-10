package com.cobble.takeaway.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cobble.takeaway.dao.RelUserRoleMapper;
import com.cobble.takeaway.dao.UserMapper;
import com.cobble.takeaway.pojo.RelUserRolePOJO;
import com.cobble.takeaway.pojo.RolePOJO;
import com.cobble.takeaway.pojo.UserPOJO;
import com.cobble.takeaway.pojo.UserSearchPOJO;
import com.cobble.takeaway.service.UserService;
import com.cobble.takeaway.spring.security.MyUser;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RelUserRoleMapper relUserRoleMapper;

	@Override
	public int insert(UserPOJO userPOJO) throws Exception {
		int ret = 0;
		ret = userMapper.insert(userPOJO);
		if (!CollectionUtils.isEmpty(userPOJO.getRoleId())) {
			for (Long roleId : userPOJO.getRoleId()) {
				RelUserRolePOJO relUserRolePOJO = new RelUserRolePOJO();
				relUserRolePOJO.setUserId(userPOJO.getUserId());
				relUserRolePOJO.setRoleId(roleId);
				relUserRoleMapper.insert(relUserRolePOJO);
			}
		}
		return ret;
	}

	@Override
	public int update(UserPOJO userPOJO) throws Exception {
		int ret = 0;
		ret = userMapper.update(userPOJO);
		return ret;
	}

	@Override
	public List<UserPOJO> finds(
			UserSearchPOJO userSearchPOJO) throws Exception {
		List<UserPOJO> ret = null;
		ret = userMapper.finds(userSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(UserSearchPOJO userSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = userMapper.getCount(userSearchPOJO);
		return ret;
	}

	@Override
	public UserPOJO findById(Long id) throws Exception {
		UserPOJO ret = null;
		ret = userMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Long id) throws Exception {
		int ret = 0;
		ret = userMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Long[] ids) throws Exception {
		int ret = 0;
		if (ids == null) {
			return ret;
		}
		for (Long id: ids) {
			ret += userMapper.deleteById(id);
		}
		return ret;
	}

	@Override
	public UserPOJO findUserByName(String username) throws Exception {
		return userMapper.findUserByName(username);
	}
	
	@Override
	public MyUser findMyUserByName(String username) throws Exception {
		UserPOJO userPOJO = new UserPOJO();
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		
		try {
			userPOJO = userMapper.findUserByName(username);
			if (userPOJO == null) {
				throw new UsernameNotFoundException("Not Found username = " + username);
			}
			List<RolePOJO> rolePOJOs = userPOJO.getRolePOJOs();
			if (!CollectionUtils.isEmpty(rolePOJOs)) {
				for (RolePOJO rolePOJO : rolePOJOs) {
					SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(rolePOJO.getRoleName());
					authorities.add(grantedAuthority);
				}
			}

			SimpleGrantedAuthority anonymousAuthority = new SimpleGrantedAuthority("ROLE_ANONYMOUS");
			authorities.add(anonymousAuthority);
			SimpleGrantedAuthority baseAuthority = new SimpleGrantedAuthority("ROLE_BASE");
			authorities.add(baseAuthority);
			if (MyUser.MEDIA.equalsIgnoreCase(userPOJO.getUserType())) {
				SimpleGrantedAuthority userTypeAuthority = new SimpleGrantedAuthority("ROLE_MEDIA");
				authorities.add(userTypeAuthority);
			} else if (MyUser.ENTERPRISE.equalsIgnoreCase(userPOJO.getUserType())) {
				SimpleGrantedAuthority userTypeAuthority = new SimpleGrantedAuthority("ROLE_ENTERPRISE");
				authorities.add(userTypeAuthority);
			} else if (MyUser.PERSON.equalsIgnoreCase(userPOJO.getUserType())) {
				SimpleGrantedAuthority userTypeAuthority = new SimpleGrantedAuthority("ROLE_PERSON");
				authorities.add(userTypeAuthority);
			} else if (MyUser.ADMIN.equalsIgnoreCase(userPOJO.getUserType())) {
				SimpleGrantedAuthority userTypeAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
				authorities.add(userTypeAuthority);
			} else if (MyUser.GUEST.equalsIgnoreCase(userPOJO.getUserType())) {
				SimpleGrantedAuthority userTypeAuthority = new SimpleGrantedAuthority("ROLE_GUEST");
				authorities.add(userTypeAuthority);
			} else {
				SimpleGrantedAuthority userTypeAuthority = new SimpleGrantedAuthority("ROLE_UNKNOWN");
				authorities.add(userTypeAuthority);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//User ret = new User(userPOJO.getUsername(), userPOJO.getPassword(), true, true, true, true, authorities);
		MyUser ret = new MyUser(username, userPOJO.getPassword(), authorities, userPOJO.getUserType());
		ret.setUserId(userPOJO.getUserId());
		ret.setNickname(userPOJO.getNickname());
		ret.setEmail(userPOJO.getEmail());
		return ret;
	}
	

	@Override
	public MyUser createPrincipalByName(String username, HttpSession session)
			throws Exception {

		MyUser myUser = this.findMyUserByName(username);

		String openId = (String) session.getAttribute("openId");
		String unionId = (String) session.getAttribute("unionId");
		if (StringUtils.isNotBlank(openId)) {
			myUser.setOpenId(openId);
		}
		if (StringUtils.isNotBlank(unionId)) {
			myUser.setUnionId(unionId);
		}
		//List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS");
		Collection<GrantedAuthority> authorities = myUser.getAuthorities();
		
		UsernamePasswordAuthenticationToken anAnthentication = new UsernamePasswordAuthenticationToken(myUser, myUser.getPassword(), authorities);
		SecurityContextHolder.getContext().setAuthentication(anAnthentication);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.info("principal instanceof MyUser: " + (principal instanceof MyUser));
		if (principal instanceof MyUser) {
			myUser = (MyUser) principal;
			session.setAttribute("username", myUser.getUsername());
			session.setAttribute("userType", myUser.getUserType());
			session.setAttribute("myUser", myUser);
		}
		return myUser;
	}

	@Override
	public int updatePassword(UserPOJO userPOJO) throws Exception {
		int ret = 0;
		ret = userMapper.updatePassword(userPOJO);
		return ret;
	}

	@Override
	public int updateInfo(UserPOJO userPOJO) throws Exception {
		int ret = 0;
		ret = userMapper.updateInfo(userPOJO);
		return ret;
	}

	@Override
	public UserPOJO findUserByNickname(String nickname) throws Exception {
		return userMapper.findUserByNickname(nickname);
	}

	@Override
	public UserPOJO findUserByIndexCode(String wxIndexCode) throws Exception {
		UserPOJO ret = null;
		ret = userMapper.findUserByIndexCode(wxIndexCode);
		return ret;
	}

	@Override
	public UserPOJO findUserByActivityId(Long activityId) throws Exception {
		UserPOJO ret = null;
		ret = userMapper.findUserByActivityId(activityId);
		return ret;
	}

	@Override
	public UserPOJO findUser4IndexCodeByUserId(Long userId)
			throws Exception {
		UserPOJO ret = null;
		ret = userMapper.findUser4IndexCodeByUserId(userId);
		return ret;
	}

	@Override
	public UserPOJO findUserByInteractiveId(Long interactiveId)
			throws Exception {
		UserPOJO ret = null;
		ret = userMapper.findUserByInteractiveId(interactiveId);
		return ret;
	}

}
