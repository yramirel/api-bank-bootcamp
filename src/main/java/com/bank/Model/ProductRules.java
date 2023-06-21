package com.bank.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.math.BigDecimal;

@Document(value = "product_rules")
@AllArgsConstructor
@Data
@Builder
public class ProductRules implements Serializable{
    @Id
    private String id;
    @DocumentReference(lazy = true)
    private TypeClient typeClient;
    @DocumentReference(lazy = true)
    private Product product;
    private Integer max_account;
    private BigDecimal cost_maintenance;
    private Integer max_deposits;
    private Integer max_withdrawal;
    private int state;
}
