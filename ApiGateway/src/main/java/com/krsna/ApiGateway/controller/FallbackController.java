package com.krsna.ApiGateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @GetMapping("/fallback-call")
    public Mono<String> myCircuitBreakerFallback(){
        return Mono.just("Our Service is down, please contact support team !!");
    }
}
