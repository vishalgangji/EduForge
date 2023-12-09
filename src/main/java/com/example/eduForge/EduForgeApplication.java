package com.example.eduForge;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EduForgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EduForgeApplication.class, args);
		System.out.println("hello");
		 
	    }
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	}
	

