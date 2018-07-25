package com.helloboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.helloboot.dao")
public class HellobootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HellobootApplication.class, args);
	}
}
