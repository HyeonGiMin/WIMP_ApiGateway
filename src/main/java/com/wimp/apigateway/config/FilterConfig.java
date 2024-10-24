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
                .route(r -> r.path("/demo/**")
                        .filters(f -> f
                                .rewritePath("/demo/api/(?<remaining>.*)", "/api/" + getApiVersion() + "/${remaining}") // RewritePath filter with API_VERSION
                        )
                        .uri("http://localhost:8082"))
                // redirect to
                .route(r -> r.path("/servicediscovery","/eureka", "/eureka/**")
                        .filters(f -> f
                                .setStatus(HttpStatus.FOUND)  // 302 상태 코드로 설정
                                .redirect(302, "http://oci-hyeoni1995.duckdns.org:8761"))  // 리다이렉트할 URL
                        .uri("http://oci-hyeoni1995.duckdns.org:8761"))  // uri는 필요 없음
                .build();
    }

    // Helper method to resolve API_VERSION from environment or use 'default'
    private String getApiVersion() {
        return System.getenv().getOrDefault("API_VERSION", "v1");
    }
}
