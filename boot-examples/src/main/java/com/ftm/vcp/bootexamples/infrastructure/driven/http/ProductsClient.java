package com.ftm.vcp.bootexamples.infrastructure.driven.http;

import com.ftm.vcp.bootexamples.infrastructure.driven.http.dto.ProductDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface ProductsClient {

    @GetExchange("/products/{id}")
    ProductDto getProductById(@PathVariable Long id);
}
