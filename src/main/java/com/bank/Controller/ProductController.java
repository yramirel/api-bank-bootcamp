package com.bank.Controller;

import com.bank.Model.Product;
import com.bank.Model.Request.ProductRequest;
import com.bank.Service.ProductRulesService;
import com.bank.Service.ProductService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value ="/product")
    @ResponseStatus(HttpStatus.OK)
    public Maybe<Product> saveProduct(@RequestBody ProductRequest productRequest) {
        return productService.saveProduct(productRequest);
    }
    @GetMapping(value ="/product/{codeProduct}")
    @ResponseStatus(HttpStatus.OK)
    public Maybe<Product> getCient(@PathVariable(value = "codeProduct") String codeProduct) {
        Maybe<Product> product=null;
        try {
            product= productService.getProduct(codeProduct);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return product;
    }
    @DeleteMapping(value ="/product/{codeProduct}")
    @ResponseStatus(HttpStatus.OK)
    public Maybe<Product> deleteProduct(@PathVariable(value = "codeProduct") String codeProduct) {
        Maybe<Product> client=null;
        try {
            client= productService.deleteProduct(codeProduct);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return client;
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
}
