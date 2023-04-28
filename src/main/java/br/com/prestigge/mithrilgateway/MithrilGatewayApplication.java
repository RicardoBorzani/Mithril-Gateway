package br.com.prestigge.mithrilgateway;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.runApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

import org.springframework.context.annotation.Bean;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableConfigurationProperties(UriConfiguration.class)
@RestController
public class MithrilGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MithrilGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {
		String httpUri = uriConfiguration.getHttpbin();

		CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
				.failureRateThreshold(50)
				.waitDurationInOpenState(Duration.ofMillis(10000))
				.permittedNumberOfCallsInHalfOpenState(5)
				.slidingWindowSize(10)
				.minimumNumberOfCalls(5)
				.build();

		return builder.routes()
				.route(p -> p
						.path("/auth/**")
						.filters(f -> f
								.circuitBreaker(config -> config
										.setName("authCircuitBreaker")
										.setFallbackUri("forward:/authFallback")
										//.setCircuitBreakerConfig(circuitBreakerConfig)
								)
								.retry(config -> config
										.setRetries(3)
										//.setStatuses(HttpStatus.BAD_GATEWAY)
								)
								//.hystrix(config -> config
										//.setName("authHystrixCommand")
										//.setFallbackUri("forward:/authFallback")
								//)
								//.requestRateLimiter(config -> config
										//.setKeyResolver(new SpELClientKeyResolver())
										//.setRateLimiter(new RedisRateLimiter(10, 20))
								//)
								//.timeout(config -> config
										//.setResponseTimeout(Duration.ofMillis(2000))
								//		)
						)
						.uri(httpUri))
				.build();
	}

	@RequestMapping("/fallback")
	public Mono<String> fallback() {
		return Mono.just("fallback");
	}
}



@ConfigurationProperties
class UriConfiguration {

	private String httpbin = "http://httpbin.org:80";

	public String getHttpbin() {
		return httpbin;
	}

	public void setHttpbin(String httpbin) {
		this.httpbin = httpbin;
	}
}
