package com.bank.Controller;

import com.bank.Service.ClientService;
import com.bank.Service.ProductRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @PostMapping(value ="/client")
    public void saveClient(@RequestBody Map params) {
        try {
            clientService.saveClient(params);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
