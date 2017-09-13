package com.skcc.hoeuni.security.vo;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@SuppressWarnings("serial")
@Data
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class User implements UserDetails {

	@NonNull private String username;
	@NonNull private String password;
	
	@NonNull private boolean isAccountNonExpired;
	@NonNull private boolean isAccountNonLocked;
	@NonNull private boolean isCredentialsNonExpired;
	@NonNull private boolean isEnabled;
	
	private Collection<GrantedAuthority>  authorities;
	private Collection<String> roles;
	
	@NonNull private String name;
	
	public void setRoles(Collection<String> roles) {
		this.roles = roles;
		
		authorities = new ArrayList<>();
		roles.forEach((authority) -> authorities.add(new SimpleGrantedAuthority(authority)));
	}
		
}
