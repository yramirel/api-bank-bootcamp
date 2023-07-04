package com.bank.Service;

import com.bank.Model.ClientProduct;
import com.bank.Model.Request.ClientProductRequest;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;


public interface ClientProductService {
    public Maybe<ClientProduct> saveClientProduct(ClientProductRequest clientProductRequest) throws Exception;
    public Flowable<ClientProduct> getClientProductByDocumentNumber(String documentNumber) throws Exception;
}
