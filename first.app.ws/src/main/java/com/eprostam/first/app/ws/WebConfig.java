package com.eprostam.first.app.ws;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		registry
			.addMapping("/**") // "/users"
			.allowedMethods("*") // "GET", "POST", ... etc
			.allowedOrigins("*"); // "url1", "url2" .... etc
	} 
}
