package com.bank.Service;

import com.bank.Model.Client;
import com.bank.Model.Product;
import com.bank.Model.Request.ClientRequest;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

import java.util.Map;

public interface ClientService {
    public Maybe<Client> saveClient(ClientRequest client);
    public Maybe<Client> deleteClient(String document_number);
    public Maybe<Client> getClient(String document_number) throws Exception;
    public Flowable<Client> listClient(String document_number) throws Exception;
}
