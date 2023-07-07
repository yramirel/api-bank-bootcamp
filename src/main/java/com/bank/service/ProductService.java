package com.bank.service;

import com.bank.model.Product;
import com.bank.model.request.ProductRequest;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

/**
 * ProductService interface.
 */
public interface ProductService {
  public Flowable<Product> listProducts() throws Exception;

  public Maybe<Product> saveProduct(ProductRequest productRequest);

  public Maybe<Product> getProduct(String codeProduct) throws Exception;

  public Maybe<Product> deleteProduct(String codeProduct);
}
