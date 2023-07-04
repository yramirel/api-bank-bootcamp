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

@Document(value = "bank_debs")
@AllArgsConstructor
@Data
@Builder
public class BankDebs {
    @Id
    private String id;
    private Client client;
    private String documentNumber;
    private LocalDateTime startDate;
    private LocalDateTime cutoffDate;
    private LocalDateTime expirationDate;
    @DocumentReference(lazy = true)
    private ClientProduct clientProduct;
    private String accountNumber;
    private BigDecimal amount;
    private int state;
}
