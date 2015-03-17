package com.cobble.takeaway.spring.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MyUser extends User {
	public static String GUEST = "GUEST";
	public static String PERSON = "PERSON";
	public static String ENTERPRISE = "ENTERPRISE";
	
	/**
	 * GUEST, PERSON, ENTERPRISE
	 */
	private String userType = "GUEST";
	
	public MyUser() {
		this("guest", "guest", null);
	}

	public MyUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}

	public MyUser(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this(username, password, true, true, true, true, authorities);
	}
	public MyUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, String userType) {
		this(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		this.userType = userType;
	}
	
	public MyUser(String username, String password,
			Collection<? extends GrantedAuthority> authorities, String userType) {
		this(username, password, true, true, true, true, authorities);
		this.userType = userType;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
