package com.tantanmen.carbofootprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
public class CarboFootprintApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarboFootprintApplication.class, args);
	}

}
