package com.eazyBytes.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
		serverHttpSecurity
				.authorizeExchange(exchange -> exchange.pathMatchers("/eazybank/accounts/**").authenticated()
						.pathMatchers("/eazybank/cards/**").authenticated().pathMatchers("/eazybank/loans/**")
						.authenticated().pathMatchers(HttpMethod.GET, "/**").permitAll().anyExchange().authenticated())
				.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
				.csrf(ServerHttpSecurity.CsrfSpec::disable);

		return serverHttpSecurity.build();
	}
}
