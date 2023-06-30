package com.bank.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(value = "transaction")
@AllArgsConstructor
@Data
@Builder
public class Transactions {
    @Id
    private String id;
    private Client client;
    private String documentNumber;
    private LocalDateTime date;
    private String typeTransaction;//deposito,retiro,
    @DocumentReference(lazy = true)
    private ClientProduct clientProduct;
    private String accountNumber;
    private String accountNumberReceiver;
    private BigDecimal amount;
    private int state;
}
