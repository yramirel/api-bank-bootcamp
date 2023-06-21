package com.bank.Repository;

import com.bank.Model.Product;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

public interface ProductRepository  extends RxJava3CrudRepository<Product,Integer> {
}
