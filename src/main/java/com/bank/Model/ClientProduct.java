package com.bank.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(value = "client")
@AllArgsConstructor
@Data
@Builder
public class ClientProduct implements Serializable{
    private int id;
    private String product_id;
    private String client_id;
    private LocalDateTime date;
    private BigDecimal credit_limit;
    private int state;
}
