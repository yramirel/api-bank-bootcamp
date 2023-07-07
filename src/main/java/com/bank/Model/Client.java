package com.bank.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
@Document(value = "client")
@AllArgsConstructor
@Data
@Builder
public class Client implements Serializable {
    @Id
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String documentNumber;
    private int typeClient;//natural, juridica
    private int signature;
    private int state;
}
