package com.skcc.hoeuni.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.GrantedAuthority;

import com.skcc.hoeuni.security.vo.User;

@Mapper
public interface UserMapper {

	public User getUser(String username); 
	public List<String> listAuthority(String username);
	public void insertUser(User user);
	public void insertAuthority(User user);
	public void deleteUser(String username);
	public void deleteAuthority(String username);
}
