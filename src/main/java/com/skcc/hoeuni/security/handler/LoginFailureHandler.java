package com.skcc.hoeuni.security.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.Setter;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Setter
	private String defaultFailureUrl;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String targetUrl = defaultFailureUrl;

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		String data = StringUtils.join(new String[] { " { \"success\" : false , \"targetUrl\" : \"", targetUrl, "\" ", "} " });
		PrintWriter out = response.getWriter();
		out.print(data);
		out.flush();
		out.close();
	}

}
