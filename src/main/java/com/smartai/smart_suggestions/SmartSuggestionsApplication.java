package com.smartai.smart_suggestions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer

public class SmartSuggestionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartSuggestionsApplication.class, args);
	}

}
