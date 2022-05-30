package com.company.GeneratePromoKod;

import com.company.GeneratePromoKod.continent.Request;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeneratePromoKodApplication {

	public static void main(String[] args) {

		Request request = new Request();
		request.start();

		SpringApplication.run(GeneratePromoKodApplication.class, args);
	}

}
