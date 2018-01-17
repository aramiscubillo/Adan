package com.test.adan.adan.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@ComponentScan(basePackages = {"com.test.adan"})
@EnableAutoConfiguration
@EnableJpaRepositories("com.test.adan.adan.repositories")
@EnableTransactionManagement
@EntityScan(basePackages = "com.test.adan.adan.jpa")
@EnableScheduling
@EnableAsync
public class AdanApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdanApplication.class, args);
	}
}
