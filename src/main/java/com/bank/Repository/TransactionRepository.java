package com.bank.Repository;

import com.bank.Model.ClientProduct;
import io.reactivex.rxjava3.core.Flowable;
import org.springframework.data.mongodb.repository.Query;
import com.bank.Model.Transactions;
import org.springframework.data.repository.reactive.RxJava3SortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TransactionRepository extends RxJava3SortingRepository<Transactions,String> {

    Flowable<Transactions> getByAccountNumber(String accountNumber);

    @Query(value = "{'accountNumber':?0,'date':{$gte: ?1,$lte: ?2}}")
    Flowable<Transactions> getByAccountNumberAndDateBetween(String accountNumber,LocalDateTime startDate,LocalDateTime endDate);

    @Query("{'date':{ $gte: ?0, $lte: ?1 }}")
    Flowable<Transactions> getByDateBetween(LocalDateTime startDate,LocalDateTime endDate);
}
