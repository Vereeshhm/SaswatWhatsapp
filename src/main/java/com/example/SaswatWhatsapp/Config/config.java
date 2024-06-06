package com.example.SaswatWhatsapp.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;


@Configuration
public class config {

	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
//	@Bean
//	public JdbcTemplate jdbcTemplate() {
//		return new JdbcTemplate();
//	}
}
