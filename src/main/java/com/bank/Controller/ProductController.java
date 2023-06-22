package com.bank.Controller;

import com.bank.Model.Product;
import com.bank.Service.ProductRulesService;
import com.bank.Service.ProductService;
import io.reactivex.rxjava3.core.Flowable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
@RequestMapping("/")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRulesService productRulesService;

    @RequestMapping(value ="/products" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flowable<Product> listProducts() {
        Flowable<Product> products=null;
        try {
            products= productService.listProducts();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return products;
    }

    @RequestMapping(value ="/saveProductRules" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProductRules() {

        try {
            productRulesService.saveProductRules();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @PostMapping(value ="/clientproduct")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveClient(@RequestBody Map params) {
        try {
            productService.saveClientProduct(params);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
