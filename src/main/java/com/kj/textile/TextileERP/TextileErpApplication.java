package com.kj.textile.TextileERP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TextileErpApplication {

	public static void main(String[] args) {
		SpringApplication.run(TextileErpApplication.class, args);
	}

}
