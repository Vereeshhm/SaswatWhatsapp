package com.example.SaswatWhatsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@ComponentScan(basePackages = { "com.example.SaswatWhatsapp" })
@EnableAutoConfiguration
public class SaswatWhatsappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaswatWhatsappApplication.class, args);
	}

}
