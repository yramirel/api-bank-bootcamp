package com.bank.Repository;

import com.bank.Model.ClientProduct;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;
import com.bank.Model.Transactions;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transactions,String> {
    @Query(value = "db.client_product.aggregate({$lookup:{from :'client',localField:'client',foreignField:'_id',as:'cliente'}},{$match:{'cliente.document_number':document_number,'account_number':account_number}})")
    Flowable<ClientProduct> getByDocumentNumberAndAccountNumber(String document_number);
}
