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
                .route("auth", r -> r.path("/auth/**").uri("http://localhost:8080/auth-service"))
                .route("user", r -> r.path("/users/**").uri("http://localhost:8080/user-service"))
                .route("config", r -> r.path("/config/**").uri("http://localhost:8080/config-service"))
                .route("transactions", r -> r.path("/transactions/**").uri("http://transactions-service:8080"))
                .route("calculations", r -> r.path("/calculations/**").uri("http://calculations-service:8080"))
                .route("reports", r -> r.path("/reports/**").uri("http://reports-service:8080"))
                .route("import", r -> r.path("/import/**").uri("http://import-service:8080"))
                .route("logs", r -> r.path("/api/logs/**").uri("http://logs-service:8080"))
                .route("finance", r -> r.path("/finance/**").uri("http://finance-service:8080"))
                .build();
    }

}
