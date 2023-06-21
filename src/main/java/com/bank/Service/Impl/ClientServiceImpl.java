package com.bank.Service.Impl;

import com.bank.Model.Client;
import com.bank.Model.TypeClient;
import com.bank.Repository.ClientRepository;
import com.bank.Repository.ProductRepository;
import com.bank.Repository.TypeClientRepository;
import com.bank.Service.ClientService;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TypeClientRepository typeClientRepository;
    @Override
    public void saveClient(Map params) throws Exception{
        Single<TypeClient> typeClient = typeClientRepository.getByName(params.get("type_client").toString());
        Client client= Client.builder()
                .name(params.get("name").toString())
                .documentNumber(params.get("document_number").toString())
                .signature(Integer.parseInt(params.get("signature").toString()))
                .state(1)
                .type_client(typeClient.blockingGet())
                .build();
        clientRepository.save(client).subscribe();
    }
}
