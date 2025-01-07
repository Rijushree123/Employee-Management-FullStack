package com.coffeebreak.ems_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EmsBackendApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(EmsBackendApplication.class, args);
		Student student = new Student();
		student.setId(1);
		student.setName("Rijushree");
		student.setEmail("riju@gmail.com");
		System.out.println(student.toString());
	}

}
