package com.bank.Controller;

import com.bank.Model.Transactions;
import com.bank.Service.TransactionsService;
import io.reactivex.rxjava3.core.Flowable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping(value ="/transaction")
    public void saveClient(@RequestBody Map params) {
        try {
            transactionService.saveTransaction(params);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
