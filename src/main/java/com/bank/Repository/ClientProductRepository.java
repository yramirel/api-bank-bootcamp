package com.bank.Repository;

import com.bank.Model.ClientProduct;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientProductRepository  extends ReactiveMongoRepository<ClientProduct,String> {
    Single<ClientProduct> getByAccountNumber(String account_number);

    //@Query(value = "{$lookup:{from :'client',localField:'client',foreignField:'_id',as:'cliente'}},pipeline:[{$match:{'cliente.document_number':?0,'account_number':?1}}]")
    @Query(value = "db.client_product.find()")
    Flowable<ClientProduct> listAllClientProduct();
}
