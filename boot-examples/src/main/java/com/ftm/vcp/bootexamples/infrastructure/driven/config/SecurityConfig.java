package com.ftm.vcp.bootexamples.infrastructure.driven.config;

import com.ftm.vcp.bootexamples.infrastructure.driving.rest.ExampleController;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(authorizeRequestConfigurer -> authorizeRequestConfigurer
                        .anyRequest().authenticated())
                .exceptionHandling(exceptionHandlingConfigurer -> exceptionHandlingConfigurer
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .build();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE) // Important, otherwise SecurityFilterChains are resolved in the order of declaration
    SecurityFilterChain httpBasicSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .securityMatcher(ExampleController.PROTECTED_HTTP_BASIC_FOOS_URL)
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    SecurityFilterChain h2SecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .securityMatcher(PathRequest.toH2Console())
                .authorizeHttpRequests(authorizedRequestConfigurer -> authorizedRequestConfigurer
                        .anyRequest().permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                // https://docs.spring.io/spring-security/site/docs/4.0.2.RELEASE/reference/html/headers.html#headers-frame-options
                .headers(headerConfigurer -> headerConfigurer
                        .frameOptions()
                        .sameOrigin()) // we could also have used .disabled()
                .build();
    }

    @Bean
    UserDetailsService users() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("user")
                        .password("{noop}password")
                        .roles("HTTP_BASIC_USER")
                        .build()
        );
    }
}
