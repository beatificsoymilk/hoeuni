package com.skcc.hoeuni.security.vo;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@ToString
@RequiredArgsConstructor
public class User implements UserDetails {

	@NonNull @Getter @Setter private String username;
	@NonNull @Getter @Setter private String password;
	
	@NonNull @Setter private String isAccountNonExpired;
	@NonNull @Setter private String isAccountNonLocked;
	@NonNull @Setter private String isCredentialsNonExpired;
	@NonNull @Setter private String isEnabled;
	
	@Getter @Setter private Collection<GrantedAuthority>  authorities;
	@Getter private Collection<String> roles;
	
	@NonNull @Getter @Setter private String name;
	
	
	
	public void setRoles(Collection<String> roles) {
		this.roles = roles;
		
		authorities = new ArrayList<>();
		roles.forEach((authority) -> authorities.add(new SimpleGrantedAuthority(authority)));
	}

	public boolean isAccountNonExpired() {
		return "Y".equals(isAccountNonExpired);
	}

	public boolean isAccountNonLocked() {
		return "Y".equals(isAccountNonLocked);
	}

	public boolean isCredentialsNonExpired() {
		return "Y".equals(isCredentialsNonExpired);
	}

	public boolean isEnabled() {
		return "Y".equals(isEnabled);
	}

		
}
