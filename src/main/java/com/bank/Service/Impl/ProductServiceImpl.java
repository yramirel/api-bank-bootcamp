package com.bank.Service.Impl;

import com.bank.ErrorHandler.ConflictException;
import com.bank.Model.Client;
import com.bank.Model.ClientProduct;
import com.bank.Model.Product;
import com.bank.Model.Request.ClientRequest;
import com.bank.Model.Request.ProductRequest;
import com.bank.Repository.ClientProductRepository;
import com.bank.Repository.ClientRepository;
import com.bank.Repository.ProductRepository;
import com.bank.Service.ProductService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ClientProductRepository clientProductRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Override
    public Flowable<Product> listProducts(){
        return  productRepository.listProducts();
    }
    @Override
    public Maybe<Product> saveProduct(ProductRequest productRequest){
        return productRepository.getByName(productRequest.getName()).isEmpty()
                .flatMap(isEmpty->{
                    productRequest.setState(1);
                    return isEmpty?productRepository.save(productRequest.toProduct())
                            :Single.error(new ConflictException("Error"));
                }).toMaybe();
    }
    @Override
    public Maybe<Product> getProduct(String codeProduct) throws Exception{
        return this.productRepository.getByCodeProduct(codeProduct);
    }
    @Override
    public Maybe<Product> deleteProduct(String codProduct){
        return productRepository.getByCodeProduct(codProduct).toSingle()
                .flatMap(product->{
                    product.setState(0);
                    return productRepository.save(product);
                }).toMaybe();
    }
}
