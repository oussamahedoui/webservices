package com.techtest.mobilab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RestController
@EnableSwagger2
@CrossOrigin(origins = "http://localhost:4200")
public class MobilabApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobilabApplication.class, args);
	}

}
