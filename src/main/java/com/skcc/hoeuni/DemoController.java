package com.skcc.hoeuni;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DemoController {
	
	@RequestMapping("/api/hello")
	public String greet() {
		log.error("hello");
		return "Hello";
	}

}
