package com.atriviss.raritycheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RaritycheckApplication {
	public static void main(String[] args) {
		SpringApplication.run(RaritycheckApplication.class, args);
	}
}
