package com.bank.Repository;

import com.bank.Model.Product;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends ReactiveMongoRepository<Product,String> {

    @Query(value = "db.product.find()")
    Flowable<Product> listProducts();
    Single<Product> getByName(String name);
}
