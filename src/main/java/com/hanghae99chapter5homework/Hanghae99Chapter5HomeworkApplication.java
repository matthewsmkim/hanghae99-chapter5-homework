package com.hanghae99chapter5homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Hanghae99Chapter5HomeworkApplication {

	public static void main(String[] args) {

		SpringApplication.run(Hanghae99Chapter5HomeworkApplication.class, args);
	}

}
