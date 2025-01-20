package com.pixel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.pixel.repository")
public class HelloVaadinApp {

	public static void main(String[] args) {
		SpringApplication.run(HelloVaadinApp.class, args);
	}

}
