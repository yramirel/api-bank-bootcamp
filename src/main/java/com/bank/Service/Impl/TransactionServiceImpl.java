package com.bank.Service.Impl;

import com.bank.ErrorHandler.ConflictException;
import com.bank.Model.*;
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
import java.time.LocalDateTime;
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
                    ProductRules productRules=productRulesRepository.getByCodeProduct(clientProduct.getCodeProduct()).blockingGet();

                    BigDecimal balance=clientProduct.getBalance();
                    BigDecimal amount=transactionsRequest.getAmount();

                    if(transactionsRequest.getTypeTransaction().equals("deposito")){

                    }else if(transactionsRequest.getTypeTransaction().equals("retiro") && balance.compareTo(amount)==1){

                    }else if(transactionsRequest.getTypeTransaction().equals("consumo") && clientProduct.getCreditLimit().compareTo(amount)==1){

                    }else if(transactionsRequest.getTypeTransaction().equals("pagos")){

                    }else{
                        return Single.error(new ConflictException("Transaccion no soportada"));
                    }
                    Transactions transactions= transactionsRequest.toTransactions();
                    transactions.setClient(client);
                    transactions.setClientProduct(clientProduct);
                    transactions.setDate(LocalDateTime.now());
                    return transactionRepository.save(transactions);

                }).toMaybe();

       /* Maybe<Client> client = clientRepository.getByDocumentNumber(transactionsRequest.getDocumentNumber());
        Single<ClientProduct> clientProduct = clientProductRepository.getByAccountNumber(params.get("account_number").toString());
        String typeTransaction=params.get("type_transaction").toString();

        ClientProduct clientProductUpdate=clientProduct.blockingGet();


        BigDecimal balance=clientProductUpdate.getBalance();
        BigDecimal amount=BigDecimal.valueOf(Double.parseDouble(params.get("amount").toString()));
        if(typeTransaction.equals("deposito")){
            Transactions transactions= Transactions.builder()
                    .client(client.blockingGet())
                    .clientProduct(clientProductUpdate)
                    .amount(amount)
                    .build();
            transactionRepository.save(transactions).subscribe();

            clientProductUpdate.setBalance(balance.add(amount));
            clientProductRepository.save(clientProductUpdate).subscribe();
        }else if(typeTransaction.equals("retiro") && balance.compareTo(amount)==1){
            Transactions transactions= Transactions.builder()
                    .client(client.blockingGet())
                    .clientProduct(clientProduct.blockingGet())
                    .amount(amount)
                    .build();
            transactionRepository.save(transactions).subscribe();

            clientProductUpdate.setBalance(balance.subtract(amount));
            clientProductRepository.save(clientProductUpdate).subscribe();
        }else{
            System.out.println("operacion no soportada");
        }
        return null;*/
    }
    @Override
    public Flowable<Transactions> getTransactionsByAccountNumber(String accountNumber) throws Exception{
        return transactionRepository.getByAccountNumber(accountNumber);
    }
    @Override
    public Single<BigDecimal> getBalanceByClientProduct(String account_number) throws Exception{
        Flowable<ClientProduct> clientProductSingle=clientProductRepository.listAllClientProduct();
        clientProductSingle=clientProductSingle.filter(item->{
            System.out.println(item);
            return item.getAccountNumber().equals(account_number);
        });
        Single<BigDecimal> response=clientProductSingle.map(item->{
            return Single.just(item.getBalance());
        }).blockingFirst();
        return response;
    }
}
