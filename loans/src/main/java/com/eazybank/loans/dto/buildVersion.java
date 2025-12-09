package com.eazybank.loans.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "build")
public record buildVersion(String version) {

}
