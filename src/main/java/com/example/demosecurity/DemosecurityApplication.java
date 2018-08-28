package com.example.demosecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.AllArgsConstructor;

@SpringBootApplication(scanBasePackages = "com.example.demosecurity")
@AllArgsConstructor
public class DemosecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemosecurityApplication.class, args);
	}
}
