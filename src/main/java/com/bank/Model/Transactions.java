package com.bank.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

@Document(value = "transaction")
@AllArgsConstructor
@Data
@Builder
public class Transactions {
    @Id
    private Integer id;
	@DocumentReference(lazy = true)
    private Client client;
    private LocalDateTime date;
    private String type_transaction;//deposito,retiro,
	@DocumentReference(lazy = true)
    private ClientProduct client_product;
    private int state;
}
