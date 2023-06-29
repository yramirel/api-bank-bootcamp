package com.bank.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(value = "client_product")
@AllArgsConstructor
@Data
@Builder
public class ClientProduct implements Serializable{
    @Id
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
}
