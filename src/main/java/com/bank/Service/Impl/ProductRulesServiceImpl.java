package com.bank.Service.Impl;

import com.bank.Model.Product;
import com.bank.Model.ProductRules;
import com.bank.Model.TypeClient;
import com.bank.Repository.ClientRepository;
import com.bank.Repository.ProductRepository;
import com.bank.Repository.ProductRulesRepository;
import com.bank.Repository.TypeClientRepository;
import com.bank.Service.ProductRulesService;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductRulesServiceImpl implements ProductRulesService {
    @Autowired
    private ProductRulesRepository productRulesRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TypeClientRepository typeClientRepository;
    @Override
    public void saveProductRules() throws Exception {
        try {
            Single<Product> product = productRepository.getByName("Ahorro");
            Single<TypeClient> typeClient = typeClientRepository.getByName("Juridica");

            ProductRules rule=ProductRules.builder()
                    .typeClient(typeClient.blockingGet())
                    .product(product.blockingGet())
                    .max_account(0)
                    .cost_maintenance(BigDecimal.ZERO)
                    .max_deposits(0)
                    .max_withdrawal(0)
                    .state(1)
                    .build();
            productRulesRepository.save(rule).subscribe();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
