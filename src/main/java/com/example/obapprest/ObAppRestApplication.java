package com.example.obapprest;

import com.example.obapprest.entity.Laptop;
import com.example.obapprest.repository.LaptopRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "API REST Laptops",
				version = "1.0",
				description = "My first API REST in Swagger",
				termsOfService = "/",
				contact = @Contact(
						name = "Mr. Eduar",
						email = "mail@mail.com"
				),
				license = @License(
						name = "License",
						url = "https://www.youtube.com/watch?v=Eo6v01KUeZM"
				)
		)
)
public class ObAppRestApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ObAppRestApplication.class, args);

		LaptopRepository laptopRepository = context.getBean(LaptopRepository.class);

		//CRUD
		//Create
		Laptop lenovo = new Laptop(1L, "Lenovo A6", 1000.00);
		Laptop azus = new Laptop(2L, "Azus", 1200.00);

		//Comprobate
		System.out.println("Laptos count without save => " + laptopRepository.findAll().size());

		//Save in DB
		laptopRepository.save(lenovo);
		laptopRepository.save(azus);

		//Comprobate
		System.out.println("Laptos count with save => " + laptopRepository.findAll().size());
	}

}
