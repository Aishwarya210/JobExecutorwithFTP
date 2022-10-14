package org.kp.poc.csv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * This is SpringBootApplication class
 * {@link SpringBootApplication}
 *
 */

@SpringBootApplication
@EnableScheduling
public class PocCsvApplication {


	public static void main(String[] args) {
		SpringApplication.run(PocCsvApplication.class, args);
	}

	
}
