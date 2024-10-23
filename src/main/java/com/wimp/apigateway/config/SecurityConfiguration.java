package com.wimp.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
////    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
////    private String jwkSetUri;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf->csrf.disable())
//                .authorizeHttpRequests((authorization) -> authorization
////                        .requestMatchers("/second-service/**").permitAll()  // Allow public access
////                        .requestMatchers("/servicediscovery", "/eureka", "/eureka/**").permitAll()  // Allow public access
//                        .anyRequest().permitAll()  // Other requests require authentication
//                )
////                .oauth2ResourceServer((oauth2) -> oauth2.jwt(jwt -> jwt
////                        .jwkSetUri(jwkSetUri)  // Use injected value
////                ))
//                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        return http.build();
//    }
//}
