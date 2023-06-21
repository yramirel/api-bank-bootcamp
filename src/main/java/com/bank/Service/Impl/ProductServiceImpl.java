package com.bank.Service.Impl;

import com.bank.Model.Client;
import com.bank.Model.ClientProduct;
import com.bank.Model.Product;
import com.bank.Model.TypeClient;
import com.bank.Repository.ClientProductRepository;
import com.bank.Repository.ClientRepository;
import com.bank.Repository.ProductRepository;
import com.bank.Service.ProductService;
import io.reactivex.rxjava3.core.Flowable;
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
    public void saveClientProduct(Map params) throws Exception{
        try {
            Single<Client> client = clientRepository.getByDocumentNumber(params.get("document_number").toString());
            Single<Product> product = productRepository.getByName(params.get("product_name").toString());
            ClientProduct clientProduct=ClientProduct.builder()
                    .client(client.blockingGet())
                    .product(product.blockingGet())
                    .creditLimit(BigDecimal.valueOf(Double.valueOf(params.get("credit_limit").toString())))
                    .balance(BigDecimal.ZERO)
                    .accountNumber(params.get("account_number").toString())
                    .date(LocalDateTime.now())
                    .build();
            clientProductRepository.save(clientProduct).subscribe();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
