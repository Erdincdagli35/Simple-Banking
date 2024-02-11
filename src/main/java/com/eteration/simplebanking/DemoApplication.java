package com.eteration.simplebanking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main class for starting the simple banking application.
 */
@SpringBootApplication
@EnableJpaRepositories("com.eteration.simplebanking.repository")
@EntityScan("com.eteration.simplebanking.model")
public class DemoApplication {

	/**
	 * Main method to start the application.
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
