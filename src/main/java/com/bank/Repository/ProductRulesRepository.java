package com.bank.Repository;

import com.bank.Model.ProductRules;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

public interface ProductRulesRepository extends RxJava3CrudRepository<ProductRules,Integer> {
}
