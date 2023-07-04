package com.bank.Repository;

import com.bank.Model.BankDebs;
import io.reactivex.rxjava3.core.Flowable;
import org.springframework.data.repository.reactive.RxJava3SortingRepository;

import java.time.LocalDateTime;

public interface BankDebsRepository extends RxJava3SortingRepository<BankDebs,String> {
    Flowable<BankDebs> getByAccountNumberAndStartDateAndCutoffDate(String accountNumber,LocalDateTime startDate ,LocalDateTime cutoffDate);
}
