package com.bank.service;

import com.bank.model.ClientProduct;
import com.bank.model.request.ClientProductRequest;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

/**
 * ClientProductService Interface.
 */
public interface ClientProductService {
  public Maybe<ClientProduct> saveClientProduct(ClientProductRequest clientProductRequest);

  public Flowable<ClientProduct> getClientProductByDocumentNumber(String documentNumber);
}
