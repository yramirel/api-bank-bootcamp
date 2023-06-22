package com.bank.Service;

import com.bank.Model.Client;
import com.bank.Model.Product;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

import java.util.Map;

public interface ClientService {
    public void saveClient(Map params) throws Exception;
    public Single<Client> getClient(String document_number) throws Exception;
    public Flowable<Client> listClient(String document_number) throws Exception;
}
