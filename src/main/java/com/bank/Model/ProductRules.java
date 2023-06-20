package com.bank.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;

@Document(value = "product_rules")
@AllArgsConstructor
@Data
@Builder
public class ProductRules implements Serializable{
    private int id;
    private Integer type_client_id;
    private Integer product_id;
    private Integer max_account;
    private BigDecimal cost_maintenance;
    private Integer max_deposits;
    private Integer max_withdrawal;
    private int state;
}
