package com.krsna.ApiGateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    private final String userServiceId;

    private final String ratingServiceId;

    private final String hotelServiceId;

    public ProjectConfig(
            @Value("${user.service.id}") String userServiceId,
            @Value("${hotel.service.id}") String hotelServiceId,
            @Value("${rating.service.id}") String ratingServiceId
            ){
        this.userServiceId = userServiceId;
        this.hotelServiceId = hotelServiceId;
        this.ratingServiceId = ratingServiceId;
    }
    @Bean
    public RouteLocator myCustomRoutes(RouteLocatorBuilder builder){

        RouteLocator myRoutes = builder.routes().
                route( "user-route", r ->
                        r.path("/users/**")
                                 .filters(f -> f.rewritePath("/users/?(?<remaining>.*)", "/${remaining}"))
                                 .uri(userServiceId))
                    .route("hotel-route", r ->
                        r.path("/hotels/**")
                                .filters(f -> f.rewritePath("/hotels/?(?<remaining>.*)", "/${remaining}"))
                                .uri(hotelServiceId))
                    .route("rating-route", r ->
                        r.path("/ratings/**")
                                .filters(f -> f.rewritePath("/ratings/?(?<remaining>.*)", "/${remaining}"))
                                .uri(ratingServiceId))
                .build();

        return myRoutes;
    }
}
