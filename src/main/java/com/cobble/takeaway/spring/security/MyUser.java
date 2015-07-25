package com.cobble.takeaway.spring.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MyUser extends User {
	public static String GUEST = "GUEST";
	public static String PERSON = "PERSON";
	public static String ENTERPRISE = "ENTERPRISE";
	public static String ADMIN = "ADMIN";
	
	/**
	 * GUEST, PERSON, ENTERPRISE
	 */
	private String userType = "GUEST";
	private Long userId;

	private String nickname = "";
	private String email = "";
	
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
	
	/**
	 * 返回用户信息， 去掉密码
	 * @param myUser
	 */
	public MyUser(MyUser myUser) {
		this(myUser.getUsername(), "", myUser.isEnabled(), myUser.isAccountNonExpired(), myUser.isCredentialsNonExpired(),
				myUser.isAccountNonLocked(), myUser.getAuthorities());
		this.userType = myUser.getUserType();
		this.nickname = myUser.getNickname();
		this.userId = myUser.getUserId();
		this.email = myUser.getEmail();
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(": ");
        sb.append("UserType: ").append(this.userType).append("; ");

        return sb.toString();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
