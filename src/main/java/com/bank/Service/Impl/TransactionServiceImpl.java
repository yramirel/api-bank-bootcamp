package com.bank.Service.Impl;

import com.bank.ErrorHandler.ConflictException;
import com.bank.Model.*;
import com.bank.Model.Request.ClientProductRequest;
import com.bank.Model.Request.TransactionsRequest;
import com.bank.Repository.*;
import com.bank.Service.TransactionsService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

@Service
public class TransactionServiceImpl implements TransactionsService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientProductRepository clientProductRepository;
    @Autowired
    private ProductRulesRepository productRulesRepository;
    @Override
    public Maybe<Transactions> saveTransaction(TransactionsRequest transactionsRequest) throws Exception{
        return clientRepository.getByDocumentNumber(transactionsRequest.getDocumentNumber()).toSingle()
                .flatMap(client -> {
                    ClientProduct clientProduct = clientProductRepository.getByAccountNumber(transactionsRequest.getAccountNumber()).blockingGet();
                    Boolean successRules=this.verifyRules(client,clientProduct,transactionsRequest);
                    if(successRules){
                        BigDecimal balance=clientProduct.getBalance();
                        BigDecimal amount=transactionsRequest.getAmount();
                        BigDecimal consumption=clientProduct.getConsumption();

                        if(transactionsRequest.getTypeTransaction().equals("deposito")){
                            balance=balance.add(amount);
                        }else if(transactionsRequest.getTypeTransaction().equals("retiro") && balance.compareTo(amount)==1){
                            balance=balance.subtract(amount);
                        }else if(transactionsRequest.getTypeTransaction().equals("consumo") && clientProduct.getCreditLimit().compareTo(amount.add(consumption))==1){
                            consumption=consumption.add(amount);
                        }else if(transactionsRequest.getTypeTransaction().equals("pago")){
                            consumption=consumption.subtract(amount);
                        }else if(transactionsRequest.getTypeTransaction().equals("transferencia") && balance.compareTo(amount)==1){
                            balance=balance.subtract(amount);
                            ClientProduct clientProductReceiver = clientProductRepository.getByAccountNumber(transactionsRequest.getAccountNumberReceiver()).blockingGet();
                            clientProductReceiver.setBalance(clientProductReceiver.getBalance().add(amount));
                            clientProductRepository.save(clientProductReceiver).subscribe();
                        }else{
                            return Single.error(new ConflictException("Transaccion no soportada"));
                        }

                        clientProduct.setBalance(balance);
                        clientProduct.setConsumption(consumption);
                        clientProductRepository.save(clientProduct).subscribe();

                        Transactions transactions= transactionsRequest.toTransactions();
                        transactions.setClient(client);
                        transactions.setClientProduct(clientProduct);
                        transactions.setDate(LocalDateTime.now());
                        transactions.setState(1);
                        return transactionRepository.save(transactions);
                    }else{
                        return Single.error(new ConflictException("El ciente no cumple con las Reglas de Negocio"));
                    }
                }).toMaybe();
    }
    public Boolean verifyRules(Client client, ClientProduct clientProduct,TransactionsRequest transactionsRequest){
        Boolean successRules=true;
        ProductRules productRules=null;
        Flowable<ProductRules> productRulesFlowable=productRulesRepository.getByCodeProduct(clientProduct.getCodeProduct())
                .filter(item->item.getTypeClient()==client.getTypeClient());
        Long totalTransactions=transactionRepository.getByAccountNumberAndDateBetween(transactionsRequest.getAccountNumber(), LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0), LocalDate.now().plusMonths(1).withDayOfMonth(1).minusDays(1).atTime(LocalTime.MAX)).count().blockingGet();

        if(!productRulesFlowable.isEmpty().blockingGet()){
            productRules=productRulesFlowable.blockingFirst();
        }
        if(productRules!=null){
            successRules=productRules.getMaxDeposits()==-1?true:(productRules.getMaxDeposits()>=totalTransactions.intValue()?true:false);
        }
        return successRules;
    }
    @Override
    public Flowable<Transactions> getTransactionsByAccountNumber(String accountNumber) throws Exception{
        return transactionRepository.getByAccountNumber(accountNumber);
    }
    @Override
    public Single<BigDecimal> getBalanceByClientProduct(String account_number) throws Exception{
        return clientProductRepository.getByAccountNumber(account_number)
                .switchIfEmpty(Single.just(ClientProduct.builder().build()))
                .flatMap(item->{
                    return Single.just(item.getBalance());
                });
    }
}
