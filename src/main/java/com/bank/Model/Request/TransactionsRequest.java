package com.bank.Model.Request;

import com.bank.Model.Client;
import com.bank.Model.ClientProduct;
import com.bank.Model.Transactions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@AllArgsConstructor
@Data
@Builder
public class TransactionsRequest {
    private String id;
    private Client client;
    private String documentNumber;
    private LocalDateTime date;
    private String typeTransaction;//deposito,retiro,
    private ClientProduct clientProduct;
    private String accountNumber;
    private BigDecimal amount;
    private BigDecimal consumption;
    private int state;

    public Transactions toTransactions(){
        return Transactions.builder()
                .id(this.id)
                .client(this.client)
                .documentNumber(this.documentNumber)
                .date(this.date)
                .typeTransaction(this.typeTransaction)
                .clientProduct(this.clientProduct)
                .accountNumber(this.accountNumber)
                .amount(this.amount)
                .state(this.state)
                .build();
    }
}
