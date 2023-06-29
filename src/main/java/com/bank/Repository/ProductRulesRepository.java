package com.bank.Repository;

import com.bank.Model.Product;
import com.bank.Model.ProductRules;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRulesRepository extends ReactiveMongoRepository<ProductRules,String> {
    Flowable<ProductRules> getByCodeProduct(String codeProduct);
}
