package com.example.back_to_flutter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;




@SpringBootApplication
public class BackToFlutterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackToFlutterApplication.class, args);

	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
