package com.bank.Service;

import com.bank.Model.Client;
import com.bank.Model.Product;
import com.bank.Model.Request.ProductRequest;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

import java.util.Map;

public interface ProductService {
    public Flowable<Product> listProducts() throws Exception;
    public Maybe<Product> saveProduct(ProductRequest productRequest);
    public Maybe<Product> getProduct(String codeProduct) throws Exception;
    public Maybe<Product> deleteProduct(String codeProduct);
}
