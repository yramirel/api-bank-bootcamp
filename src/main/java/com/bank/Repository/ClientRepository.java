package com.bank.Repository;

import com.bank.Model.Client;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.RxJava3SortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository  extends RxJava3SortingRepository<Client,String> {
    Maybe<Client> getByDocumentNumber(String document_number);
    @Query("{state:1}")
    Flowable<Client> getAllClient();
}
