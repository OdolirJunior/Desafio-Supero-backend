package com.odolirprojetosupero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OdolirprojetosuperoApplication {
	public static void main(String[] args) {
		SpringApplication.run(OdolirprojetosuperoApplication.class, args);
	}
}
