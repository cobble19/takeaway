package com.cobble.takeaway.spring.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;

import com.cobble.takeaway.pojo.RolePOJO;
import com.cobble.takeaway.pojo.UserPOJO;
import com.cobble.takeaway.service.UserService;

public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserPOJO userPOJO = new UserPOJO();
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		try {
			userPOJO = userService.findUserByName(username);
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

}
