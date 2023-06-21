package com.bank.Controller;

import com.bank.Model.Transactions;
import com.bank.Service.TransactionsService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

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
    @RequestMapping(value ="/balance/{account_number}")
    public Single<BigDecimal> listTransactions(@PathVariable(value = "account_number") String account_number) {
        Single<BigDecimal> balance=Single.just(BigDecimal.ZERO);
        try {
            balance= transactionService.getBalanceByClientProduct(account_number);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return balance;
    }
    @PostMapping(value ="/transaction")
    public void saveClient(@RequestBody Map params) {
        try {
            transactionService.saveTransaction(params);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
