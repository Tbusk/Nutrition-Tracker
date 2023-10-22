package com.SENG315.SpringJPA.Security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.SENG315.SpringJPA.domain.User.User;

/**
 * This is a class that is used in the Spring Security suite for a user with methods required to implement a login system.
 */
public class MyUserPrincipal implements UserDetails {

	// Variables
	private static final long serialVersionUID = -3659259589475609476L;
	String ROLE_PREFIX = "ROLE_";
	private User user;

	public MyUserPrincipal(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority(ROLE_PREFIX + user.getRole()));
		return list;
	}

	@Override
	public String getPassword() {
		if (user != null) {
			return user.getPassword();
		} else {
			return null;
		}
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
