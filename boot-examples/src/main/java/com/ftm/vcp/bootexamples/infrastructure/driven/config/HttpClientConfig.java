package com.ftm.vcp.bootexamples.infrastructure.driven.config;

import com.ftm.vcp.bootexamples.infrastructure.driven.http.ProductsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.logging.Logger;

@Configuration
public class HttpClientConfig {

    private final Logger log = Logger.getLogger(getClass().getName());

    @Bean
    ProductsClient productsClient(@Value("${product.url}") String productUrl) {

        final var factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(WebClient.builder()
                                                                                                .baseUrl("http://" + productUrl)
                                                                                                .filter(retryFilter())
                                                                                                .build()))
                                                   .build();
        return factory.createClient(ProductsClient.class);
    }

    private ExchangeFilterFunction retryFilter() {
        return (request, next) ->
                next.exchange(request)
                    .retryWhen(
                            Retry.fixedDelay(3, Duration.ofSeconds(1))
                                 .doAfterRetry(retrySignal -> log.info("Retrying...")));
    }
}
