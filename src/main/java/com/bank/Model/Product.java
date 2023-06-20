package com.bank.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(value = "product")
@AllArgsConstructor
@Data
@Builder
public class Product implements Serializable{
    private int id;
    private String type_product;//pasivo,activo
    private String name;
    private String card_number;
    private int state;
}
