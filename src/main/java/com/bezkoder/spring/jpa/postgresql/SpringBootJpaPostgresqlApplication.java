package com.bezkoder.spring.jpa.postgresql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class SpringBootJpaPostgresqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaPostgresqlApplication.class, args);
	}

}
