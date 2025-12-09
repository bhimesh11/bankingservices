package com.eazybank.cards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.eazybank.cards.constants.CardsConstants;
import com.eazybank.cards.dto.CardContactInfo;
import com.eazybank.cards.dto.buildVersion;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableConfigurationProperties({ CardContactInfo.class, buildVersion.class })
@OpenAPIDefinition(info = @Info(title = "Cards microservice REST API Documentation",
description = "EazyBank Cards microservice REST API Documentation",
version = "v1",
contact = @Contact(
		name = "Bhimesh Ganji",
	email = "bhimeshganji05@gmailcom"
),
license = @License(
		name = "Apache 2.0",
		url = "https://www.eazybytes.com"
)
),
externalDocs = @ExternalDocumentation(
description = "EazyBank Cards microservice REST API Documentation",
url = "https://www.eazybytes.com/swagger-ui.html"))
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
