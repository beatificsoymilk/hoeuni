package com.skcc.hoeuni;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan("com.skcc.hoeuni")
@SpringBootApplication
public class HoeuniApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoeuniApplication.class, args);
	}
}
