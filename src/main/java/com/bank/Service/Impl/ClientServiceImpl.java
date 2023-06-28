package com.bank.Service.Impl;

import com.bank.ErrorHandler.ConflictException;
import com.bank.Model.Client;
import com.bank.Model.Request.ClientRequest;
import com.bank.Repository.ClientRepository;
import com.bank.Service.ClientService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Maybe<Client> saveClient(ClientRequest client){
        return clientRepository.getByDocumentNumber(client.getDocumentNumber()).isEmpty()
                .flatMap(isEmpty->{
                    client.setState(1);
                    return isEmpty?clientRepository.save(client.toClient())
                            :Single.error(new ConflictException("Error"));
                }).toMaybe();
    }
    @Override
    public Maybe<Client> deleteClient(String document_number){
        return clientRepository.getByDocumentNumber(document_number).toSingle()
                .flatMap(client->{
                    client.setState(0);
                    return clientRepository.save(client);
                }).toMaybe();
    }
    @Override
    public Maybe<Client> getClient(String document_number) throws Exception{
        return this.clientRepository.getByDocumentNumber(document_number);
    }

    @Override
    public Flowable<Client> listClient(String document_number) throws Exception{
        return this.clientRepository.getAllClient();
    }
}
