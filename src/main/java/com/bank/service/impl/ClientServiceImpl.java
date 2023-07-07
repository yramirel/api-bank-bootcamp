package com.bank.service.impl;

import com.bank.errorhandler.ConflictException;
import com.bank.model.Client;
import com.bank.model.request.ClientRequest;
import com.bank.repository.ClientRepository;
import com.bank.service.ClientService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClientServiceImpl Class fron client.
 */
@Service
public class ClientServiceImpl implements ClientService {
  @Autowired
  private ClientRepository clientRepository;

  @Override
  public Maybe<Client> saveClient(ClientRequest client) {
    return clientRepository.getByDocumentNumber(client.getDocumentNumber()).isEmpty()
               .flatMap(isEmpty -> {
                 client.setState(1);
                 return isEmpty ? clientRepository.save(client.toClient())
                            : Single.error(new ConflictException("El Cliente ya Existe"));
               }).toMaybe();
  }

  @Override
  public Maybe<Client> deleteClient(String documentNumber) {
    return clientRepository.getByDocumentNumber(documentNumber).toSingle()
               .flatMap(client -> {
                 client.setState(0);
                 return clientRepository.save(client);
               }).toMaybe();
  }

  @Override
  public Maybe<Client> getClient(String documentNumber) throws Exception {
    return this.clientRepository.getByDocumentNumber(documentNumber);
  }

  @Override
  public Flowable<Client> listClient(String documentNumber) throws Exception {
    return this.clientRepository.getAllClient();
  }
}
