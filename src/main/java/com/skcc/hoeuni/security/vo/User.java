package com.skcc.hoeuni.security.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class User implements UserDetails {

	private String username;
	private String password;
	
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialsNonExpired;
	private boolean isEnabled;
	
	private Collection<? extends GrantedAuthority>  authorities;
	private Collection<String> roles;
	
	private String name;
		
}
