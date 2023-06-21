package com.bank.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
@Document(value = "client")
@AllArgsConstructor
@Data
@Builder
public class Client implements Serializable {
    @Id
    private Integer id;
    private String first_name;
    private String last_name;
    private String document_number;
	@DocumentReference(lazy = true)
    private TypeClient typeClient;//natural, juridica
    private int signature;
    private int state;
}
