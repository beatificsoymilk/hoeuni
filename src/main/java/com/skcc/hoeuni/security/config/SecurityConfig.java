package com.skcc.hoeuni.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.skcc.hoeuni.security.handler.LoginFailureHandler;
import com.skcc.hoeuni.security.handler.LoginSuccessHandler;
import com.skcc.hoeuni.security.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;

	@Autowired
	private LoginSuccessHandler successHandler;

	@Autowired
	private LoginFailureHandler failureHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String loginPage = "/login";
		String loginProcessingUrl = "/uaa/loginProcess";
		String defaultSuccessUrl = "/dashboard/home";
		String expiredUrl = "/login";
		String logoutUrl = "/uaa/logout";
		String logoutSuccessUrl = "/login";

		successHandler.setDefaultSuccessUrl(defaultSuccessUrl);
		failureHandler.setDefaultFailureUrl(loginPage);

		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		    .and()
			.authorizeRequests().anyRequest().authenticated()
//		    .antMatchers("/admin").access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
//			.antMatchers("/meta").permitAll()
//			.antMatchers("/**").authenticated()
			.and()
			.formLogin().loginPage(loginPage)
			            .usernameParameter("id")
			            .passwordParameter("password")
				        .loginProcessingUrl(loginProcessingUrl)
				        .successHandler(successHandler)
				        .failureHandler(failureHandler)
			.and()
			.sessionManagement().maximumSessions(1).expiredUrl(expiredUrl)
			                    .and()
			.and()
			.logout().logoutUrl(logoutUrl)
				     .logoutSuccessUrl(logoutSuccessUrl)
				     .invalidateHttpSession(true)
				     .deleteCookies("JSESSIONID");
//		    .and()
//		    .rememberMe().rememberMeParameter("rememberMe")
//		                 .tokenValiditySeconds(1209600) /* 2주 */
//		                 .key("auto");

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(userService.getPasswordEncoder());
//		auth.userDetailsService(userService);
	}

}
