package com.bank.Model.Request;

import com.bank.Model.Client;
import com.bank.Model.ClientProduct;
import com.bank.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@AllArgsConstructor
@Data
@Builder
public class ClientProductRequest {
    private String id;
    private Product product;
    private String codeProduct;
    private Client client;
    private String documentNumber;
    private String accountNumber;
    private LocalDateTime date;
    private BigDecimal creditLimit;
    private BigDecimal balance;
    private BigDecimal consumption;
    private int state;

    public ClientProduct toClientProduct(){
     return ClientProduct.builder()
             .id(this.id)
             .product(this.product)
             .codeProduct(this.codeProduct)
             .client(this.client)
             .documentNumber(this.documentNumber)
             .accountNumber(this.accountNumber)
             .date(this.date)
             .creditLimit(this.creditLimit)
             .balance(this.balance)
             .consumption(this.consumption)
             .state(this.state)
             .build();
    }
}
