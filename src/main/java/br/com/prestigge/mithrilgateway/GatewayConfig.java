package br.com.prestigge.mithrilgateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.gateway.filter.factory.CircuitBreakerGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// ...

@Configuration
public class GatewayConfig {

    @Autowired
    private ReactiveResilience4JCircuitBreakerFactory circuitBreakerFactory;

    @Bean
    public CircuitBreakerGatewayFilterFactory circuitBreakerGatewayFilterFactory() {
        return new CircuitBreakerGatewayFilterFactory(circuitBreakerFactory);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Rota de exemplo com Circuit Breaker
                .route("example", r -> r.path("/example")
                        .filters(f -> f.circuitBreaker(c -> c.setName("myCircuitBreaker")))
                        .uri("http://localhost:8080"))
                .build();
    }

}
