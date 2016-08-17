package com.cobble.takeaway.spring.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cobble.takeaway.service.UserService;

public class MyUserDetailsService implements UserDetailsService {
	private final static Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		MyUser ret = null;
		try {
			ret = userService.findMyUserByName(username);
		} catch (Exception e1) {
			logger.error("Can't get MyUser by username = {}", username);
		}
		return ret;
	}

}
