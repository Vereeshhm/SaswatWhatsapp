package com.example.SaswatWhatsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})

@ComponentScan(basePackages = { "com.example.SaswatWhatsapp" })

@EnableJpaRepositories(basePackages = "com.example.SaswatWhatsapp.Repository")
public class SaswatWhatsappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaswatWhatsappApplication.class, args);
	}

}
