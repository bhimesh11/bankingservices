package com.eazybank.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.eazybank.accounts.dto.AccountsContactInfoDto;
import com.eazybank.accounts.dto.BuildVersion;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableConfigurationProperties({ AccountsContactInfoDto.class, BuildVersion.class })
@EnableJpaAuditing
@OpenAPIDefinition(info = @Info(title = "Accounts MicroService REST API documentation",
description = "Eazybank Accounts MicroService REST API documentation",
version = "v1",
contact = @Contact(name = "Bhimesh Ganji",email = "BhimeshGanji5@gmail.com"),
license = @License(name = "apache 2.9")),externalDocs = @ExternalDocumentation(description =" Eazy Bank accounts microservkice API documentation" ))
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
