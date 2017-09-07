package com.skcc.hoeuni.security.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.skcc.hoeuni.security.vo.User;

public interface UserService extends UserDetailsService {
	Collection<GrantedAuthority> loadAuthorities(String username);
	public User getUser(String username);
	public void createUser(User user);
	public void deleteUser(String username);
	public PasswordEncoder passwordEncoder();
}
