package com.skcc.hoeuni.security.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skcc.hoeuni.security.mapper.UserMapper;
import com.skcc.hoeuni.security.vo.User;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Getter
	private final PasswordEncoder passwordEncoder;
	
	public UserServiceImpl() {
		passwordEncoder = new BCryptPasswordEncoder(); 
	}
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("username {}", username);
		User user = userMapper.getUser(username);
		log.info("user {}", user);
//		User user = new User("beatific", passwordEncoder.encode("1234567"), true, true, true, true, "beatificho");
		
		if(user == null) throw new UsernameNotFoundException("username[" + username + "]");
		
		user.setRoles(getRoles(username));
		return user;
	}

	public Collection<String> getRoles(String username) {

		Collection<String> roles = new ArrayList<>();
		roles.add("ADMIN");
		roles.add("USER");
//		return userMapper.listAuthority(username);
		return roles;
				
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
