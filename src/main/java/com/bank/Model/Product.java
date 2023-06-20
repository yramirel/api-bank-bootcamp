package com.bank.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
@Builder
public class Product implements Serializable{
    private int id;
    private String first_name;
    private String document_number;
    private int signature;
    private int state;
}
