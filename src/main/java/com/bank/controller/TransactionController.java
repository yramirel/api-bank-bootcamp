package com.bank.controller;

import com.bank.model.Transactions;
import com.bank.model.request.TransactionsRequest;
import com.bank.service.TransactionsService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * TransactionController class.
 */
@RestController
@RequestMapping("/")
public class TransactionController {
  @Autowired
  private TransactionsService transactionService;

  /**
   * listTransactions method.
   *
   * @return ,
   */
  @GetMapping(value = "/transactions", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flowable<Transactions> listTransactions() {
    Flowable<Transactions> transactions = null;
    try {
      transactions = null; //transactionService.listProducts();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return transactions;
  }

  /**
   * getBalanceByClientProduct method.
   *
   * @param accountNumber ,
   * @return ,
   */
  @GetMapping(value = "/balance/{accountNumber}")
  @ResponseStatus(HttpStatus.OK)
  public Single<BigDecimal> getBalanceByClientProduct(@PathVariable(value = "accountNumber")
                                                      String accountNumber) {
    Single<BigDecimal> balance = Single.just(BigDecimal.ZERO);
    try {
      balance = transactionService.getBalanceByClientProduct(accountNumber);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return balance;
  }

  /**
   * saveTransaction method.
   *
   * @param transactionsRequest ,
   * @return ,
   */
  @PostMapping(value = "/transaction")
  @ResponseStatus(HttpStatus.CREATED)
  public Maybe<Transactions> saveTransaction(@RequestBody TransactionsRequest transactionsRequest) {
    Maybe<Transactions> transactions = null;
    try {
      transactions = transactionService.saveTransaction(transactionsRequest);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return transactions;
  }

  /**
   * listTransactionsByAccountNumber method.
   *
   * @param accountNumber ,
   * @return ,
   */
  @GetMapping(value = "/transactions/{accountNumber}",
      produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flowable<Transactions> listTransactionsByAccountNumber(
      @PathVariable(value = "accountNumber") String accountNumber) {
    Flowable<Transactions> transactions = null;
    try {
      transactions = transactionService.getTransactionsByAccountNumber(accountNumber);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return transactions;
  }

  /**
   * getByCommisionsByAccountNumberAndDate method.
   *
   * @param accountNumber ,
   * @return ,
   */
  @GetMapping(value = "/comission/{accountNumber}",
      produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flowable<Transactions> getByCommisionsByAccountNumberAndDate(
      @PathVariable(value = "accountNumber") String accountNumber) {
    Flowable<Transactions> transactions = null;
    try {
      transactions = transactionService.getByCommisionsByAccountNumberAndDate(accountNumber);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return transactions;
  }
}
