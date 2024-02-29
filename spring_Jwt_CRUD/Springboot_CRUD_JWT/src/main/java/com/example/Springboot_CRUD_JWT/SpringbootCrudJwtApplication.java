package com.example.Springboot_CRUD_JWT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2

public class SpringbootCrudJwtApplication {
	public static void main(String[] args) {

		SpringApplication.run(SpringbootCrudJwtApplication.class, args);
	}

}

