package com.skcc.hoeuni.security;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.skcc.hoeuni.HoeuniApplication;
import com.skcc.hoeuni.security.mapper.UserMapper;
import com.skcc.hoeuni.security.vo.User;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = HoeuniApplication.class)
//@WebAppConfiguration
public class UserMapperTest {
	@Autowired
	UserMapper userMapper;

//	@Test
	public void readUserTest() {
		User user = userMapper.getUser("beatific");
		assertThat("beatific", is(user.getUsername()));
		assertThat("beatificho", is(user.getName()));
		assertThat("1234567", is(user.getPassword()));
	}

//	@Test
	public void readAuthorityTest() {
		List<String> authorities = userMapper.listAuthority("beatific");
		assertThat(authorities, hasItems("ADMIN", "USER"));
		authorities = userMapper.listAuthority("test");
		assertThat(authorities, hasItem("USER"));
	}
}
