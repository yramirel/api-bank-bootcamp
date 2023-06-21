package com.bank.Repository;

import com.bank.Model.Client;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

public interface ClientRepository  extends RxJava3CrudRepository<Client,Integer> {
}
