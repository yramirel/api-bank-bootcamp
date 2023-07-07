package com.bank.service.impl;

import com.bank.errorhandler.ConflictException;
import com.bank.model.Product;
import com.bank.model.request.ProductRequest;
import com.bank.repository.ClientProductRepository;
import com.bank.repository.ClientRepository;
import com.bank.repository.ProductRepository;
import com.bank.service.ProductService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProductServiceImpl Class for product.
 */
@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private ClientProductRepository clientProductRepository;
  @Autowired
  private ClientRepository clientRepository;

  @Override
  public Flowable<Product> listProducts() {
    return productRepository.listProducts();
  }

  @Override
  public Maybe<Product> saveProduct(ProductRequest productRequest) {
    return productRepository.getByName(productRequest.getName()).isEmpty()
               .flatMap(isEmpty -> {
                 productRequest.setState(1);
                 return isEmpty ? productRepository.save(productRequest.toProduct())
                            : Single.error(new ConflictException("Error"));
               }).toMaybe();
  }

  @Override
  public Maybe<Product> getProduct(String codeProduct) throws Exception {
    return this.productRepository.getByCodeProduct(codeProduct);
  }

  @Override
  public Maybe<Product> deleteProduct(String codProduct) {
    return productRepository.getByCodeProduct(codProduct).toSingle()
               .flatMap(product -> {
                 product.setState(0);
                 return productRepository.save(product);
               }).toMaybe();
  }
}
