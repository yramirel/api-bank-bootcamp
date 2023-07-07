package com.bank.service;

import com.bank.model.Transactions;
import com.bank.model.request.TransactionsRequest;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import java.math.BigDecimal;

/**
 * TransactionsService Interface.
 */
public interface TransactionsService {
  public Maybe<Transactions> saveTransaction(TransactionsRequest transactionsRequest);

  public Flowable<Transactions> getTransactionsByAccountNumber(String name);

  public Single<BigDecimal> getBalanceByClientProduct(String accountNumber);

  public Flowable<Transactions> getByCommisionsByAccountNumberAndDate(String accountNumber);
}
