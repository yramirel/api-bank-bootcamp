package com.bank.controller;

import com.bank.model.Client;
import com.bank.model.request.ClientRequest;
import com.bank.service.ClientService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClientController Class.
 */
@RestController
@RequestMapping("/")
public class ClientController {
  @Autowired
  private ClientService clientService;

  @PostMapping(value = "/client")
  @ResponseStatus(HttpStatus.OK)
  public Maybe<Client> saveClient(@RequestBody ClientRequest client) {
    return clientService.saveClient(client);
  }

  /**
   * getCient method.
   *
   * @param documentNumber ,
   * @return ,
   */
  @GetMapping(value = "/client/{document_number}")
  @ResponseStatus(HttpStatus.OK)
  public Maybe<Client> getCient(@PathVariable(value = "documentNumber") String documentNumber) {
    Maybe<Client> client = null;
    try {
      client = clientService.getClient(documentNumber);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return client;
  }

  /**
   * deleteCient method.
   *
   * @param documentNumber ,
   * @return ,
   */
  @DeleteMapping(value = "/client/{documentNumber}")
  @ResponseStatus(HttpStatus.OK)
  public Maybe<Client> deleteCient(@PathVariable(value = "documentNumber") String documentNumber) {
    Maybe<Client> client = null;
    try {
      client = clientService.deleteClient(documentNumber);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return client;
  }

  /**
   * listCient method.
   *
   * @return ,
   */
  @RequestMapping(value = "/clients", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Flowable<Client> listCient() {
    Flowable<Client> client = null;
    try {
      client = clientService.listClient("");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return client;
  }
}
