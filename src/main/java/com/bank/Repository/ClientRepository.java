package com.bank.Repository;

import com.bank.Model.Client;
import com.bank.Model.Product;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository  extends ReactiveMongoRepository<Client,String> {
    Single<Client> getByDocumentNumber(String document_number);

    Flowable<Client> getByTypeClientContaining();
}
