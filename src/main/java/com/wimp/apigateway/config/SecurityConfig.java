package com.wimp.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwkSetUri;
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        // Customizing the security handling
        http
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(HttpMethod.GET,"/servicediscovery","/eureka","/eureka/**").permitAll() // Public routes
                        .pathMatchers(HttpMethod.GET,"/actuator/**").permitAll()
                        .anyExchange().authenticated() // All other routes need authentication
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwkSetUri(jwkSetUri)  // Use injected value
                        )
                );

        return http.build();
    }
}
