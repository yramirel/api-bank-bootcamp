package com.bank.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
@Document(value = "client")
@AllArgsConstructor
@Data
@Builder
public class Client implements Serializable {
    private int id;
    private String first_name;
    private String last_name;
    private String document_number;
    private String type_client_id;//natural, juridica
    private int signature;
    private int state;
}