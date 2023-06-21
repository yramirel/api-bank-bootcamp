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
	@DocumentReference(lazy = true)
    private Client client;
    private LocalDateTime date;
    @Field("type_transaction")
    private String typeTransaction;//deposito,retiro,
	@DocumentReference(lazy = true)
    private ClientProduct clientProduct;
    private BigDecimal amount;
    private int state;
}
