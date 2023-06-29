package com.ftm.vcp.bootexamples.infrastructure.driven.http.dto;

import java.math.BigDecimal;

public record ProductDto(
    Long id,
    String label,
    BigDecimal unitPrice,
    int quantityInStock
) {
}
