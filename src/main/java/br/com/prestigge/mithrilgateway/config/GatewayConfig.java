package br.com.prestigge.mithrilgateway.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class GatewayConfig implements ApplicationListener<WebServerInitializedEvent> {

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        int port = event.getWebServer().getPort();
        System.out.println("GatewayConfig initialized. Server started at port " + port);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("landing-page", r -> r.path("/").uri("http://localhost:8080/landing-page"))
                .route("login", r -> r.path("/login/**").uri("http://localhost:8080/login"))
                .route("cadastro-usuario",
                        r -> r.path("/cadastro-usuario/**").uri("http://localhost:8080/cadastro-usuario"))
                .route("logoff", r -> r.path("/logoff").uri("http://localhost:8080/logoff"))
                .build();
    }

}
