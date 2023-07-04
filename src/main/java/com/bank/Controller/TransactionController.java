package com.bank.Controller;

import com.bank.Model.Request.TransactionsRequest;
import com.bank.Model.Transactions;
import com.bank.Service.TransactionsService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/")
public class TransactionController {
    @Autowired
    private TransactionsService transactionService;

    @RequestMapping(value ="/transactions" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flowable<Transactions> listTransactions() {
        Flowable<Transactions> transactions=null;
        try {
            transactions= null;//transactionService.listProducts();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return transactions;
    }
    @RequestMapping(value ="/balance/{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Single<BigDecimal> getBalanceByClientProduct(@PathVariable(value = "accountNumber") String accountNumber) {
        Single<BigDecimal> balance=Single.just(BigDecimal.ZERO);
        try {
            balance= transactionService.getBalanceByClientProduct(accountNumber);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return balance;
    }
    @PostMapping(value ="/transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public Maybe<Transactions> saveTransaction(@RequestBody TransactionsRequest transactionsRequest) {
        Maybe<Transactions> transactions=null;
        try {
            transactions=transactionService.saveTransaction(transactionsRequest);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return transactions;
    }
    @RequestMapping(value ="/transactions/{accountNumber}" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flowable<Transactions> listTransactionsByAccountNumber(@PathVariable(value = "accountNumber") String accountNumber) {
        Flowable<Transactions> transactions=null;
        try {
            transactions= transactionService.getTransactionsByAccountNumber(accountNumber);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return transactions;
    }
    @RequestMapping(value ="/comission/{accountNumber}" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flowable<Transactions> getByCommisionsByAccountNumberAndDate(@PathVariable(value = "accountNumber") String accountNumber) {
        Flowable<Transactions> transactions=null;
        try {
            transactions= transactionService.getByCommisionsByAccountNumberAndDate(accountNumber);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return transactions;
    }
}
