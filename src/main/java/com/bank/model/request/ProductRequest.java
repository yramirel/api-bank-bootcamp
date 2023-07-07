package com.bank.model.request;

import com.bank.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * ProductRequest Class.
 */
@AllArgsConstructor
@Data
@Builder
public class ProductRequest {
  private String id;
  private String codeProduct;
  private String typeProduct;
  private String name;
  private int state;

  /**
   * toProduct mehtod.
   *
   * @return ,
   */
  public Product toProduct() {
    return Product.builder()
               .id(this.id)
               .codeProduct(this.codeProduct)
               .typeProduct(this.typeProduct)
               .name(this.name)
               .state(this.state)
               .build();
  }
}
