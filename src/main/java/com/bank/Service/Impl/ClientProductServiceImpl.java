package com.bank.Service.Impl;

import com.bank.ErrorHandler.ConflictException;
import com.bank.Model.*;
import com.bank.Model.Request.ClientProductRequest;
import com.bank.Repository.ClientProductRepository;
import com.bank.Repository.ClientRepository;
import com.bank.Repository.ProductRepository;
import com.bank.Repository.ProductRulesRepository;
import com.bank.Service.ClientProductService;
import io.reactivex.rxjava3.core.Flowable;
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
    @Autowired
    private ProductRulesRepository productRulesRepository;
    @Override
    public Maybe<ClientProduct> saveClientProduct(ClientProductRequest clientProductRequest){
        return clientRepository.getByDocumentNumber(clientProductRequest.getDocumentNumber())
                .switchIfEmpty(Single.error(new ConflictException("El Cliente No existe")))
                .flatMap(client->{
                    Maybe<Product> product=productRepository.getByCodeProduct(clientProductRequest.getCodeProduct())
                            .switchIfEmpty(Maybe.error(new ConflictException("El Producto No existe")));
                    Boolean successRules=this.verifyRules(client,clientProductRequest);
                    if(successRules){
                       clientProductRequest.setClient(client);
                       clientProductRequest.setDate(LocalDateTime.now());
                       clientProductRequest.setState(1);
                       clientProductRequest.setProduct(product.blockingGet());
                       return clientProductRepository.save(clientProductRequest.toClientProduct());
                    }else{
                       return Single.error(new ConflictException("El ciente no cumple con las Reglas de Negocio"));
                    }
                }).toMaybe();
    }
    public Boolean verifyRules(Client client,ClientProductRequest clientProductRequest){
        Boolean successRules=true;
        ProductRules productRules=null;
        Flowable<ProductRules> productRulesFlowable=productRulesRepository.getByCodeProduct(clientProductRequest.getCodeProduct())
                .filter(item->item.getTypeClient()==client.getTypeClient());
        Long numberAccountCreated=clientProductRepository.getByDocumentNumberAndCodeProduct(client.getDocumentNumber(),clientProductRequest.getCodeProduct()).count().blockingGet();

        if(!productRulesFlowable.isEmpty().blockingGet()){
            productRules=productRulesFlowable.blockingFirst();
        }
        if(productRules!=null){
            successRules=productRules.getMaxAccount()==-1?true:(productRules.getMaxAccount()>=numberAccountCreated.intValue()?false:false);
            if(clientProductRequest.getCodeProduct().equals("cuentacorrientepyme") || clientProductRequest.getCodeProduct().equals("ahorrovip")){
                successRules=this.verifyRulesAdditionals(client,clientProductRequest,productRules);
            }
        }
        return successRules;
    }
    public Boolean verifyRulesAdditionals(Client client,ClientProductRequest clientProductRequest,ProductRules productRules){
        Boolean successRules=true;
        Long numberCreditCard=clientProductRepository.getByDocumentNumberAndCodeProduct(client.getDocumentNumber(),"tarjetacredito").count().blockingGet();
        if(clientProductRequest.getCodeProduct().equals("cuentacorrientepyme")){
            Long numberCorrientAccount=clientProductRepository.getByDocumentNumberAndCodeProduct(client.getDocumentNumber(),"cuentacorriente").count().blockingGet();
            successRules=numberCorrientAccount>=1 && numberCreditCard>=1?true:false;
        }else{
            successRules=numberCreditCard>=1?true:false;
        }
        return successRules;
    }
    @Override
    public Flowable<ClientProduct> getClientProductByDocumentNumber(String documentNumber) throws Exception{
        return clientProductRepository.getByDocumentNumber(documentNumber);
    }
}
