package com.skcc.hoeuni.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.skcc.hoeuni.security.service.UserService;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String loginPage = "";
		String defaultSuccessUrl = "";
		String authenticationFailureUrl = "";
		http.csrf().disable()
		    .authorizeRequests().anyRequest().authenticated()
		    .and().formLogin().loginPage(loginPage)
		                      .defaultSuccessUrl(defaultSuccessUrl)
		                      .failureUrl(authenticationFailureUrl);
	}

	@Override protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService); 
	}

}
