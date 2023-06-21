package com.bank.Service;

import com.bank.Model.Product;
import io.reactivex.rxjava3.core.Flowable;
import reactor.core.publisher.Flux;

import java.util.Map;

public interface ProductService {
    public Flowable<Product> listProducts() throws Exception;
    public void saveClientProduct(Map params) throws Exception;
}
