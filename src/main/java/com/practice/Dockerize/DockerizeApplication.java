package com.practice.Dockerize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class DockerizeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockerizeApplication.class, args);
	}

}
