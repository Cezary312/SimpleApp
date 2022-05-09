package com.example.social_network;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class App {

	public static final String LOCAL_HOST = "http://localhost:3000";

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry)
			{
				registry.addMapping("/users").allowedOrigins(LOCAL_HOST);
				registry.addMapping("/login").allowedOrigins(LOCAL_HOST);
				registry.addMapping("/posts").allowedOrigins(LOCAL_HOST);
			}
		};
	}
}
