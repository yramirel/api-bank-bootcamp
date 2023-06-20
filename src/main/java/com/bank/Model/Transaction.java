package com.bank.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Document(value = "transaction")
@AllArgsConstructor
@Data
@Builder
public class Transaction {
    private int id;
    private String client_id;
    private LocalDateTime date;
    private String type_transaction;//deposito,retiro,
    private Integer client_product_id;
    private int state;
}
