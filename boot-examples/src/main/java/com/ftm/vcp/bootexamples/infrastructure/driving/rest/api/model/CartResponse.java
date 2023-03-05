package com.ftm.vcp.bootexamples.infrastructure.driving.rest.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ftm.vcp.bootexamples.infrastructure.driving.rest.api.model.ItemResponse;
import java.util.ArrayList;
import java.util.List;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CartResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CartResponse {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("items")
  @Valid
  private List<ItemResponse> items = null;

  @JsonProperty("totalPrice")
  private Double totalPrice;

  public CartResponse id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public CartResponse items(List<ItemResponse> items) {
    this.items = items;
    return this;
  }

  public CartResponse addItemsItem(ItemResponse itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<>();
    }
    this.items.add(itemsItem);
    return this;
  }

  /**
   * Get items
   * @return items
  */
  @Valid 
  @Schema(name = "items", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public List<ItemResponse> getItems() {
    return items;
  }

  public void setItems(List<ItemResponse> items) {
    this.items = items;
  }

  public CartResponse totalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
    return this;
  }

  /**
   * Get totalPrice
   * @return totalPrice
  */
  
  @Schema(name = "totalPrice", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CartResponse cartResponse = (CartResponse) o;
    return Objects.equals(this.id, cartResponse.id) &&
        Objects.equals(this.items, cartResponse.items) &&
        Objects.equals(this.totalPrice, cartResponse.totalPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, items, totalPrice);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CartResponse {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
    sb.append("    totalPrice: ").append(toIndentedString(totalPrice)).append("\n");
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

