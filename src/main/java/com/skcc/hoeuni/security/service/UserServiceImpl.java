package com.skcc.hoeuni.security.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Getter private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userMapper.getUser(username);
		if(user == null) throw new UsernameNotFoundException("username[" + username + "]");
		
		user.setRoles(getRoles(username));
		return user;
	}

	public Collection<String> getRoles(String username) {

		return userMapper.listAuthority(username);
				
	}

	@Override
	public User getUser(String username) {
		
		User user = userMapper.getUser(username);
		user.setRoles(getRoles(username));
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

}
