package com.example.confirmEmail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ConfirmEmailApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ConfirmEmailApplication.class, args);
	}

}
