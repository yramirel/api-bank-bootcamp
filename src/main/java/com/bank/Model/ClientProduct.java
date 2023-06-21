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
    @DocumentReference(lazy = true)
    private Product product;
    @DocumentReference(lazy = true)
    private Client client;
    @Field("account_number")
    private String accountNumber;
    private LocalDateTime date;
    @Field("credit_limit")
    private BigDecimal creditLimit;
    private BigDecimal balance;
    private int state;
}
