package com.example.web_qwiz_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WebQwizAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebQwizAppApplication.class, args);
	}

}
