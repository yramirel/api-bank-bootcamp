package com.bank.model.request;

import com.bank.model.Client;
import com.bank.model.ClientProduct;
import com.bank.model.Transactions;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * TransactionsRequest Class.
 */
@AllArgsConstructor
@Data
@Builder
public class TransactionsRequest {
  private String id;
  private Client client;
  private String documentNumber;
  private LocalDateTime date;
  private String typeTransaction; //deposito,retiro,
  private ClientProduct clientProduct;
  private String accountNumber;
  private String accountNumberReceiver;
  private BigDecimal amount;
  private BigDecimal consumption;
  private int state;

  /**
   * toTransactions method.
   *
   * @return ,
   */
  public Transactions toTransactions() {
    return Transactions.builder()
               .id(this.id)
               .client(this.client)
               .documentNumber(this.documentNumber)
               .date(this.date)
               .typeTransaction(this.typeTransaction)
               .clientProduct(this.clientProduct)
               .accountNumber(this.accountNumber)
               .accountNumberReceiver(accountNumberReceiver)
               .amount(this.amount)
               .state(this.state)
               .build();
  }
}
