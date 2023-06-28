package com.bank.Repository;

import com.bank.Model.Product;
import com.bank.Model.ProductRules;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRulesRepository extends ReactiveMongoRepository<ProductRules,String> {
    Maybe<ProductRules> getByCodeProduct(String codeProduct);
}
