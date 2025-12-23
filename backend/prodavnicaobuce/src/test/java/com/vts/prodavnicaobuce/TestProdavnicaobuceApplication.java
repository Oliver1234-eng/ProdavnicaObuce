package com.vts.prodavnicaobuce;

import org.springframework.boot.SpringApplication;

public class TestProdavnicaobuceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ProdavnicaobuceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
