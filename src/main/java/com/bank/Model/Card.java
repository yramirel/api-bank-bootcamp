package com.bank.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document(value = "card")
@AllArgsConstructor
@Data
@Builder
public class Card {
    @Id
    private String id;
    @DocumentReference(lazy = true)
    private ClientProduct clientProduct;
    private String codeProduct;
    private String accountNumber;
    private String cardNumber;
    private List<ClientProduct> associatedAccounts;
    private int state;
}
