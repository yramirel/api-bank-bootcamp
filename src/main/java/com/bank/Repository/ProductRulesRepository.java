package com.bank.Repository;

import com.bank.Model.ProductRules;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRulesRepository extends ReactiveMongoRepository<ProductRules,String> {
}
