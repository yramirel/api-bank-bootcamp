package com.bank.Service;

import com.bank.Model.Product;
import com.bank.Model.Request.TransactionsRequest;
import com.bank.Model.Transactions;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

import java.math.BigDecimal;
import java.util.Map;

public interface TransactionsService {
   public Maybe<Transactions> saveTransaction(TransactionsRequest transactionsRequest) throws Exception;
   public Flowable<Transactions> getTransactionsByAccountNumber(String name) throws Exception;
   public Single<BigDecimal> getBalanceByClientProduct(String account_number) throws Exception;
   public Flowable<Transactions> getByCommisionsByAccountNumberAndDate(String accountNumber) throws Exception;
}
