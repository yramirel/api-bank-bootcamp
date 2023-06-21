package com.bank.Repository;

import io.reactivex.rxjava3.core.Flowable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;
import com.bank.Model.Transactions;

public interface TransactionRepository extends RxJava3CrudRepository<Transactions,Integer> {
    @Query(value = "SELECT code name FROM tb_product where is_active=1 and sales_price>30")
    Flowable<String> findProductsActive();
}
