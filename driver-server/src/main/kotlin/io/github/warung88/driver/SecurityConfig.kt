package io.github.warung88.driver

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.invoke
import org.springframework.security.web.server.SecurityWebFilterChain


@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    fun filterChain(http: ServerHttpSecurity): SecurityWebFilterChain = http {
        authorizeExchange {
            authorize(anyExchange, authenticated)
        }
        oauth2ResourceServer {
            jwt { }
        }
    }
}