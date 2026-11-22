package com.eazyBytes.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                .authorizeExchange(exchange -> exchange
                        // Spring Cloud Gateway's discovery locator exposes services as
                        // /{serviceId}/** (accounts, cards, loans), not /eazybank/{service}/**.
                        .pathMatchers("/accounts/**").authenticated()
                        .pathMatchers("/cards/**").authenticated()
                        .pathMatchers("/loans/**").authenticated()
                        // Keep non-banking GET endpoints public; the service routes above
                        // are matched first and therefore still require a JWT.
                        .pathMatchers(HttpMethod.GET, "/**").permitAll()
                        .anyExchange().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .csrf(ServerHttpSecurity.CsrfSpec::disable);

        return serverHttpSecurity.build();
    }
}
