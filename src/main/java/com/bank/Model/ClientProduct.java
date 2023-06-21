package com.bank.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(value = "client_product")
@AllArgsConstructor
@Data
@Builder
public class ClientProduct implements Serializable{
    @Id
    private Integer id;
    @DocumentReference(lazy = true)
    private Product product;
    @DocumentReference(lazy = true)
    private Client client;
    private LocalDateTime date;
    private BigDecimal credit_limit;
    private BigDecimal balance;
    private int state;
}
