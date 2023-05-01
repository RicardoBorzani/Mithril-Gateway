package br.com.prestigge.mithrilgateway;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.gateway.handler.predicate.PathRoutePredicateFactory;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import org.springframework.cloud.gateway.filter.factory.CircuitBreakerGatewayFilterFactory;


import java.util.ArrayList;
import java.util.List;

@Component
public class MithrilRouteLocator implements RouteLocator {

    private final ReactiveResilience4JCircuitBreakerFactory circuitBreakerFactory;

    public MithrilRouteLocator(ReactiveResilience4JCircuitBreakerFactory circuitBreakerFactory) {
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    List<Route> routes = new ArrayList<>();

    @Override
    public Flux<Route> getRoutes() {
        CircuitBreaker circuitBreaker = CircuitBreaker.create("backend");

        routes.add(Route.async()
                .id("auth")
                .predicate(new PathRoutePredicateFactory().apply(c -> c.setPattern("/**")))
                .uri("http://localhost:8080")
                .filters(f -> new GatewayConfig().apply(f).circuitBreaker(circuitBreaker))
                .build());

        routes.add(Route.async()
                .id("auth2")
                .predicate(new PathRoutePredicateFactory().apply(c -> c.setPattern("/**")))
                .uri("http://localhost:8080")
                .filters(f -> new GatewayConfig().apply(f).circuitBreaker(circuitBreaker))
                .build());

        return Flux.fromIterable(routes);
    }


}
