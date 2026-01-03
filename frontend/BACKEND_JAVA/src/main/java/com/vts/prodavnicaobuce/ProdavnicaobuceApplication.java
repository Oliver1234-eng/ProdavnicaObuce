package com.vts.prodavnicaobuce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class ProdavnicaobuceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdavnicaobuceApplication.class, args);
	}

}
