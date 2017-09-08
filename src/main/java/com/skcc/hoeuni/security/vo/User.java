package com.skcc.hoeuni.security.vo;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
	
	private Collection<GrantedAuthority>  authorities;
	private Collection<String> roles;
	
	private String name;
	
	public void setRoles(Collection<String> roles) {
		this.roles = roles;
		
		authorities = new ArrayList<>();
		roles.forEach((authority) -> authorities.add(new SimpleGrantedAuthority(authority)));
	}
		
}
