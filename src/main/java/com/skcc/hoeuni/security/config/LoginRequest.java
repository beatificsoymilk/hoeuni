package com.skcc.hoeuni.security.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class LoginRequest {

	private String name;
	private String password;
}
