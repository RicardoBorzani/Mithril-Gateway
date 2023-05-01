package br.com.prestigge.mithrilgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
@Import(MithrilRouteLocator.class)
@EnableConfigurationProperties(UriConfiguration.class)
@RestController

public class MithrilGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MithrilGatewayApplication.class, args);
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
