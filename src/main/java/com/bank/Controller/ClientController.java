package com.bank.Controller;

import com.bank.Model.Client;
import com.bank.Model.Request.ClientRequest;
import com.bank.Service.ClientService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @PostMapping(value ="/client")
    @ResponseStatus(HttpStatus.OK)
    public Maybe<Client> saveClient(@RequestBody ClientRequest client) {
        return clientService.saveClient(client);
    }
    @GetMapping(value ="/client/{document_number}")
    @ResponseStatus(HttpStatus.OK)
    public Maybe<Client> getCient(@PathVariable(value = "document_number") String document_number) {
        Maybe<Client> client=null;
        try {
            client= clientService.getClient(document_number);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return client;
    }
    @DeleteMapping(value ="/client/{document_number}")
    @ResponseStatus(HttpStatus.OK)
    public Maybe<Client> deleteCient(@PathVariable(value = "document_number") String document_number) {
        Maybe<Client> client=null;
        try {
            client= clientService.deleteClient(document_number);
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
