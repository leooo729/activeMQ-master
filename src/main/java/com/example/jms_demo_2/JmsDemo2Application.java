package com.example.jms_demo_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = "com.example.jms_demo_2.*")
public class JmsDemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(JmsDemo2Application.class, args);
	}

}
