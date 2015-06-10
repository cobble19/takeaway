package com.cobble.takeaway.spring.security;

import java.util.Collection;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.intercept.RunAsManager;
import org.springframework.security.core.Authentication;

public class MyRunAsManager implements RunAsManager {

	@Override
	public Authentication buildRunAs(Authentication authentication,
			Object object, Collection<ConfigAttribute> attributes) {
		return null;
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
