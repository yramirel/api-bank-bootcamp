package com.bank.Repository;

import com.bank.Model.ClientProduct;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

public interface ClientProductRepository  extends RxJava3CrudRepository<ClientProduct,Integer> {
}
