package com.bank.Model.Request;

import com.bank.Model.Client;
import com.bank.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@AllArgsConstructor
@Data
@Builder
public class ProductRequest {
    private String id;
    private String codeProduct;
    private String typeProduct;
    private String name;
    private int state;

    public Product toProduct(){
        return Product.builder()
                .id(this.id)
                .codeProduct(this.codeProduct)
                .typeProduct(this.typeProduct)
                .name(this.name)
                .state(this.state)
                .build();
    }
}
