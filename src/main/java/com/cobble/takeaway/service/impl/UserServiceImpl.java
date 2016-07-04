package com.cobble.takeaway.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
	public UserPOJO findById(Integer id) throws Exception {
		UserPOJO ret = null;
		ret = userMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Integer id) throws Exception {
		int ret = 0;
		ret = userMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Integer[] ids) throws Exception {
		int ret = 0;
		if (ids == null) {
			return ret;
		}
		for (Integer id: ids) {
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


}
