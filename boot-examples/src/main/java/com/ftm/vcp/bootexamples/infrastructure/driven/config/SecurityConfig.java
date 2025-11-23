package com.ftm.vcp.bootexamples.infrastructure.driven.config;

import com.ftm.vcp.bootexamples.infrastructure.driving.rest.ExampleController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.ott.RedirectOneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.function.Supplier;

@Configuration(proxyBeanMethods = false)
@EnableMethodSecurity
// @EnableWebSecurity // Spring Boot includes the @EnableWebSecurity annotation for you. See Boot's
// SecurityAutoConfiguration class for details.
public final class SecurityConfig {

    @Bean
    // Important, otherwise SecurityFilterChains are resolved in the order of declaration
    @Order(Ordered.HIGHEST_PRECEDENCE)
    SecurityFilterChain csrfSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .securityMatcher(ExampleController.PROTECTED_CSRF_FOOS_URL)
            .authorizeHttpRequests(authorizedRequestConfigurer -> authorizedRequestConfigurer
                .anyRequest().authenticated()
            )
            .csrf(csrfConfigurer -> csrfConfigurer
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .csrfTokenRequestHandler(backwardCompatibleXorCsrfTokenHandler())
            )
            .exceptionHandling(exceptionHandlingConfigurer -> exceptionHandlingConfigurer
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
            .build();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
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
                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))// we could also have used .disabled())
            .build();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    SecurityFilterChain oneTimeTokenSecurityFilterChain(
        HttpSecurity httpSecurity,
        OneTimeTokenContextProvider ottProvider
    ) throws Exception {
        final var redirectOttSuccessHandler = new RedirectOneTimeTokenGenerationSuccessHandler("/ott/sent");
        return httpSecurity
            .securityMatcher(ExampleController.PROTECTED_LOGIN_URL, "/login", "/default-ui.css", "/ott/sent", "/ott/generate", "/login/ott")
            .authorizeHttpRequests(authorizedRequestConfigurer -> authorizedRequestConfigurer
                .requestMatchers("/ott/sent").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults())
            .oneTimeTokenLogin(ottLoginConfigurer -> ottLoginConfigurer
                .tokenGenerationSuccessHandler(((request, response, oneTimeToken) -> {
                    final var magicLink = UriComponentsBuilder.fromUriString(request.getContextPath())
                        .replaceQuery(null)
                        .fragment(null)
                        .path("/login/ott")
                        .queryParam("token", oneTimeToken.getTokenValue());

                    ottProvider.set(new OneTimeTokenContextProvider.ExistingOneTimeTokenContext(oneTimeToken));

                    System.out.println("magicLink: " + magicLink.toUriString());

                    redirectOttSuccessHandler.handle(request, response, oneTimeToken);
                })))
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

    /**
     * Combination of
     * {@link XorCsrfTokenRequestAttributeHandler#handle(HttpServletRequest, HttpServletResponse, Supplier)}
     * and {@link CsrfTokenRequestHandler#resolveCsrfTokenValue(HttpServletRequest, CsrfToken)} default implementation
     *
     * @return an implementation of CsrfTokenRequestHandler
     * @see <link>https://stackoverflow.com/questions/74447118/csrf-protection-not-working-with-spring-security-6</link>
     */
    private static CsrfTokenRequestHandler backwardCompatibleXorCsrfTokenHandler() {
        final var delegate = new XorCsrfTokenRequestAttributeHandler();
        delegate.setCsrfRequestAttributeName("_csrf");
        return delegate::handle;
//        return delegate;
        // Equivalent to:
//        return new CsrfTokenRequestHandler() {
//            @Override
//            public void handle(final HttpServletRequest request, final HttpServletResponse response,
//                               final Supplier<CsrfToken> csrfToken) {
//                delegate.handle(request, response, csrfToken);
//            }
//        };
    }

}
