package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class DemoApplication {
	/*
		Esteban Durán - 2020388144
		Kevin Quesada - 2020096838

		Punto #1
		Demo\src\main\java\com\example\demo\sd_ownersController
		Demo\src\main\java\com\example\demo\sd_owners
		Demo\src\main\java\com\example\demo\sd_problems


		Punto #2
		Demo\src\main\java\com\example\demo\conf\C3P0DataSourceProperties
		Demo\src\main\java\com\example\demo\conf\DSConf
		Demo\src\main\resources\application.properties

		Punto #3
		Demo\src\main\java\com\example\demo\sd_ownersController
		Demo\src\main\java\com\example\demo\sd_ownersRepository
		Demo\src\main\java\com\example\demo\sd_owners
		Demo\src\main\java\com\example\demo\sd_problems
	*/
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
