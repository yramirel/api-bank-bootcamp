package com.bank.Controller;

import com.bank.Model.ClientProduct;
import com.bank.Model.Product;
import com.bank.Model.Request.ClientProductRequest;
import com.bank.Service.ClientProductService;
import com.bank.Service.ClientService;
import com.bank.Service.ProductService;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
public class ClientProductController {
    @Autowired
    private ClientProductService clientProductService;
    @Autowired
    private ClientService clientService;

    @PostMapping(value ="/clientproduct")
    @ResponseStatus(HttpStatus.CREATED)
    public Maybe<ClientProduct> saveClient(@RequestBody ClientProductRequest clientProductRequest) {
        Maybe<ClientProduct> product=null;
        try {
            product=clientProductService.saveClientProduct(clientProductRequest);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return product;
    }
}
