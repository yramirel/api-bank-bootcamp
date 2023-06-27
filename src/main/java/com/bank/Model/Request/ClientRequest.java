package com.bank.Model.Request;

import com.bank.Model.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
@AllArgsConstructor
@Data
@Builder
public class ClientRequest{

    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String documentNumber;
    private int typeClient;
    private int signature;
    private int state;

    public Client toClient(){
        return Client.builder()
                .id(this.id)
                .name(this.name)
                .documentNumber(this.documentNumber)
                .typeClient(this.typeClient)
                .signature(this.signature)
                .state(this.state)
                .build();
    }
}
