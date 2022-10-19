package com.sparta.spring_5week;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Spring5weekApplication {

    public static void main(String[] args) {
        SpringApplication.run(Spring5weekApplication.class, args);
    }

}
