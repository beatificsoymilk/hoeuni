package com.skcc.hoeuni.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.skcc.hoeuni.security.mapper.UserMapper;
import com.skcc.hoeuni.security.vo.User;

import lombok.Getter;

public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userMapper.getUser(username);
		user.setAuthorities(loadAuthorities(username));
		return user;
	}

	@Override
	public Collection<GrantedAuthority> loadAuthorities(String username) {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		userMapper.getAuthority(username)
				.forEach((authority) -> authorities.add(new SimpleGrantedAuthority(authority)));

		return authorities;

	}

	@Override
	public User getUser(String username) {
		User user = userMapper.getUser(username);
		user.setAuthorities(loadAuthorities(username));
		return user;
	}

	@Override
	public void createUser(User user) {
		String password = user.getPassword();
		String encodedPassword = passwordEncoder.encode(password);
		user.setPassword(encodedPassword);
		userMapper.insertUser(user);
		userMapper.insertAuthority(user);
	}

	@Override
	public void deleteUser(String username) {
		userMapper.deleteUser(username);
		userMapper.deleteAuthority(username);
	}

	@Override
	public PasswordEncoder passwordEncoder() {
		return passwordEncoder;
	}

}
