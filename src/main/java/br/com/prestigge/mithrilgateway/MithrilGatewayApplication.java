package br.com.prestigge.mithrilgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.gateway.config.GatewayAutoConfiguration;

@Configuration
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication(exclude = GatewayAutoConfiguration.class)
@ComponentScan(basePackages = "br.com.prestigge.mithrilgateway")
public class MithrilGatewayApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MithrilGatewayApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MithrilGatewayApplication.class, args);
	}

}
