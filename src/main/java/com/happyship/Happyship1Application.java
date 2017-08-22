package com.happyship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.happyship.controllers.JwtFilter;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
public class Happyship1Application {
	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();

		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/rest/*");

		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(Happyship1Application.class, args);
	}
}
