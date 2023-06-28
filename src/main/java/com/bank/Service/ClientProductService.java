package com.bank.Service;

import com.bank.Model.ClientProduct;
import com.bank.Model.Product;
import com.bank.Model.Request.ClientProductRequest;
import io.reactivex.rxjava3.core.Maybe;

import java.util.Map;

public interface ClientProductService {
    public Maybe<ClientProduct> saveClientProduct(ClientProductRequest clientProductRequest) throws Exception;
}
