package com.wimp.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class FilterConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // first-service
//                .route(r -> r.path("/first-service/**")
//                        .uri("http://localhost:8081"))
                // second-service
//                .route(r -> r.path("/second-service/**")
//                        .filters(f -> f.addResponseHeader("second-request", "second-request-header")
//                                .addResponseHeader("second-response", "second-response-header"))
//                        .uri("http://localhost:8082"))
                // redirect to
                .route(r -> r.path("/servicediscovery","/eureka", "/eureka/**")
                        .filters(f -> f
                                .setStatus(HttpStatus.FOUND)  // 302 상태 코드로 설정
                                .redirect(302, "http://oci-hyeoni1995.duckdns.org:8761"))  // 리다이렉트할 URL
                        .uri("http://oci-hyeoni1995.duckdns.org:8761"))  // uri는 필요 없음
                .build();
    }
}
