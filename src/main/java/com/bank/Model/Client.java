package com.bank.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
@Document(value = "client")
@AllArgsConstructor
@Data
@Builder
public class Client implements Serializable {
    @Id
    private String id;
    private String name;
    @Field("document_number")
    private String documentNumber;
	@DocumentReference(lazy = true)
    private TypeClient type_client;//natural, juridica
    private int signature;
    private int state;
}
