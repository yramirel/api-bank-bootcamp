package com.bank.Repository;

import com.bank.Model.ClientProduct;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.data.repository.reactive.RxJava3SortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientProductRepository  extends RxJava3SortingRepository<ClientProduct,String> {
    Maybe<ClientProduct> getByAccountNumber(String account_number);

    Flowable<ClientProduct> getByDocumentNumberAndCodeProduct(String documentNumber, String codeProduct);

    Flowable<ClientProduct> getByDocumentNumber(String documentNumber);
}
