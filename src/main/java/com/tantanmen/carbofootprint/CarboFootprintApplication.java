package com.tantanmen.carbofootprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CarboFootprintApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarboFootprintApplication.class, args);
	}

}
