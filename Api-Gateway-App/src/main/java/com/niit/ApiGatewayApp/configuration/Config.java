package com.niit.ApiGatewayApp.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public RouteLocator getRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p->p.path("/api/v1/**")
                        .uri("lb://RestaurantApp"))
                .route(p->p.path("/api/v2/**")
                        .uri("lb://UserAuthenticationApp"))
                .build();
    }
}
