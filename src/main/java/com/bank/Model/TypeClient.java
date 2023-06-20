package com.bank.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
@Document(value = "client")
@AllArgsConstructor
@Data
@Builder
public class TypeClient implements Serializable {
    private int id;
    private String name;
    private int state;
}
