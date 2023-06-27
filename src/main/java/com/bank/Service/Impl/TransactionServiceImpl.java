package com.bank.Service.Impl;

import com.bank.Model.Client;
import com.bank.Model.ClientProduct;
import com.bank.Model.Product;
import com.bank.Model.Transactions;
import com.bank.Repository.ClientProductRepository;
import com.bank.Repository.ClientRepository;
import com.bank.Repository.ProductRepository;
import com.bank.Repository.TransactionRepository;
import com.bank.Service.TransactionsService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class TransactionServiceImpl implements TransactionsService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientProductRepository clientProductRepository;
    @Override
    public void saveTransaction(Map params) throws Exception{
        Maybe<Client> client = clientRepository.getByDocumentNumber(params.get("document_number").toString());
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
    }
    @Override
    public Flowable<Transactions> getTransactionsByClientProduct(String name) throws Exception{

        return null;
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
