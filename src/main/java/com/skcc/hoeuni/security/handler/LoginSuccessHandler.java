package com.skcc.hoeuni.security.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.Setter;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Setter
	private String defaultSuccessUrl;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		String targetUrl = defaultSuccessUrl;

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		String data = StringUtils.join(new String[] { " { \"success\" : true , \"targetUrl\" : \"", targetUrl, "\" ", "} " });
		PrintWriter out = response.getWriter();
		out.print(data);
		out.flush();
		out.close();
	}
}
