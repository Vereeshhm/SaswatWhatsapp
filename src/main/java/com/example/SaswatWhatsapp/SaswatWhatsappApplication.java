package com.example.SaswatWhatsapp;




import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;




@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})

@ComponentScan(basePackages = { "com.example.SaswatWhatsapp" })

@EnableJpaRepositories(basePackages = "com.example.SaswatWhatsapp.Repository")
public class SaswatWhatsappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaswatWhatsappApplication.class, args);
	}
	
	
	
	    
	@Bean
	public DataSource dataSource(@Value("${spring.datasource.url}") String url,
	                             @Value("${spring.datasource.username}") String username,
	                             @Value("${spring.datasource.password}") String password) {
	    return DataSourceBuilder.create()
	           .driverClassName("org.postgresql.Driver")
	           .url(url)
	           .username(username)
	           .password(password)
	           .build();
	}
	}


