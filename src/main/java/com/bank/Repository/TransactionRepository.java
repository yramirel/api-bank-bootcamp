package com.bank.Repository;

import com.bank.Model.ClientProduct;
import io.reactivex.rxjava3.core.Flowable;
import org.springframework.data.mongodb.repository.Query;
import com.bank.Model.Transactions;
import org.springframework.data.repository.reactive.RxJava3SortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends RxJava3SortingRepository<Transactions,String> {

    Flowable<Transactions> getByAccountNumber(String accountNumber);
}
