package com.ftm.vcp.bootexamples.infrastructure.driving.rest.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * AddItemRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AddItemRequest {

  @JsonProperty("product_id")
  private Long productId;

  @JsonProperty("quantity")
  private Integer quantity;

  public AddItemRequest productId(Long productId) {
    this.productId = productId;
    return this;
  }

  /**
   * id of product to add
   * @return productId
  */
  
  @Schema(name = "product_id", example = "3", description = "id of product to add", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public AddItemRequest quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * quantity to add, or updated quantity in case of existing product in cart
   * @return quantity
  */
  
  @Schema(name = "quantity", example = "2", description = "quantity to add, or updated quantity in case of existing product in cart", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddItemRequest addItemRequest = (AddItemRequest) o;
    return Objects.equals(this.productId, addItemRequest.productId) &&
        Objects.equals(this.quantity, addItemRequest.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId, quantity);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddItemRequest {\n");
    sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

