package com.example.fullStackbackEnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan("com.example.fullStackbackEnd")
public class FullstackBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(FullstackBackEndApplication.class, args);

	}

}
