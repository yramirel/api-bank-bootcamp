package com.bank.Service.Impl;

import com.bank.ErrorHandler.ConflictException;
import com.bank.Model.Client;
import com.bank.Model.ClientProduct;
import com.bank.Model.Product;
import com.bank.Model.Request.ClientProductRequest;
import com.bank.Repository.ClientProductRepository;
import com.bank.Repository.ClientRepository;
import com.bank.Repository.ProductRepository;
import com.bank.Service.ClientProductService;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClientProductServiceImpl implements ClientProductService {
    @Autowired
    private ClientProductRepository clientProductRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Maybe<ClientProduct> saveClientProduct(ClientProductRequest clientProductRequest){
        return clientRepository.getByDocumentNumber(clientProductRequest.getDocumentNumber()).toSingle()
                .flatMap(client->{
                    Maybe<Product> product=productRepository.getByCodeProduct(clientProductRequest.getCodeProduct());
                    clientProductRequest.setClient(client);
                    clientProductRequest.setDate(LocalDateTime.now());
                    clientProductRequest.setState(1);
                    clientProductRequest.setProduct(product.blockingGet());
                    return clientProductRepository.save(clientProductRequest.toClientProduct());
                }).toMaybe();
    }
}
