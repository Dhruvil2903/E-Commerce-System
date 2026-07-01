package com.dhruvil.api_gateway.route;

import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

@Configuration
public class Route {

    @Bean
    public RouterFunction<ServerResponse> handleAuthRoute(){
        return route("auth_service")
                .route(RequestPredicates.path("/api/auth/**"), HandlerFunctions.http("http://auth-service:8082"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> handleProductRoute(){
        return route("product_service")
                .route(RequestPredicates.path("/api/products/**"), HandlerFunctions.http("http://product-service:8084"))
                .build();
    }

}
