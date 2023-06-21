package com.bank.Repository;

import com.bank.Model.Product;
import com.bank.Model.TypeClient;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeClientRepository  extends ReactiveMongoRepository<TypeClient,String> {
     Single<TypeClient> getByName(@Param("name") String name);
}
