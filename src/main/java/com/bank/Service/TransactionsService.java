package com.bank.Service;

import com.bank.Model.Product;
import com.bank.Model.Transactions;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

import java.math.BigDecimal;
import java.util.Map;

public interface TransactionsService {
   public void saveTransaction(Map params) throws Exception;
   public Flowable<Transactions> getTransactionsByClientProduct(String name) throws Exception;
   public Single<BigDecimal> getBalanceByClientProduct(String account_number) throws Exception;
}
