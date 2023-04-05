package com.ems.ems.project;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;




@SpringBootApplication
//@ComponentScan(basePackages = {"com.ems.ems.project", "com.ems.ems.project.config"})
public class EmsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmsProjectApplication.class, args);
	}

}
