package com.bank.controller;

import com.bank.model.ClientProduct;
import com.bank.model.request.ClientProductRequest;
import com.bank.service.ClientProductService;
import com.bank.service.ClientService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * ClientProductController Class.
 */
@RestController
@RequestMapping("/")
public class ClientProductController {
  @Autowired
  private ClientProductService clientProductService;
  @Autowired
  private ClientService clientService;

  /**
   * saveClient method.
   *
   * @param clientProductRequest ,
   * @return ,
   */
  @PostMapping(value = "/clientproduct")
  @ResponseStatus(HttpStatus.CREATED)
  public Maybe<ClientProduct> saveClient(@RequestBody ClientProductRequest clientProductRequest) {
    Maybe<ClientProduct> product = null;
    try {
      product = clientProductService.saveClientProduct(clientProductRequest);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return product;
  }

  /**
   * getClientProductByDocumentNumber method.
   *
   * @param documentNumber ,
   * @return ,
   */
  @GetMapping(value = "/clientproduct/{documentNumber}",
      produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flowable<ClientProduct> getClientProductByDocumentNumber(
      @PathVariable(value = "documentNumber") String documentNumber) {
    Flowable<ClientProduct> transactions = null;
    try {
      transactions = clientProductService.getClientProductByDocumentNumber(documentNumber);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return transactions;
  }
}
