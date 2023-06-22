package com.bank.Controller;

import com.bank.Model.Client;
import com.bank.Model.Product;
import com.bank.Service.ClientService;
import com.bank.Service.ProductRulesService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @PostMapping(value ="/client")
    @ResponseStatus(HttpStatus.OK)
    public void saveClient(@RequestBody Map params) {
        try {
            clientService.saveClient(params);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @RequestMapping(value ="/client/{document_number}")
    @ResponseStatus(HttpStatus.OK)
    public Single<Client> getCient(@PathVariable(value = "document_number") String document_number) {
        Single<Client> client=null;
        try {
            client= clientService.getClient(document_number);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return client;
    }

    @RequestMapping(value ="/clients", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flowable<Client> listCient() {
        Flowable<Client> client=null;
        try {
            client= clientService.listClient("");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return client;
    }
}
