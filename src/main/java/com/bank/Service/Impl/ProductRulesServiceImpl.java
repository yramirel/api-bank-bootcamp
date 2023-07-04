package com.bank.Service.Impl;

import com.bank.Model.Product;
import com.bank.Model.ProductRules;
import com.bank.Repository.ProductRepository;
import com.bank.Repository.ProductRulesRepository;
import com.bank.Service.ProductRulesService;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductRulesServiceImpl implements ProductRulesService {
    @Autowired
    private ProductRulesRepository productRulesRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void saveProductRules() throws Exception {
        try {
            Maybe<Product> product = productRepository.getByName("Ahorro");
            //Single<TypeClient> typeClient = typeClientRepository.getByName("Juridica");

            ProductRules rule=ProductRules.builder()
                    .typeClient(1)
                    .codeProduct("")
                    .maxAccount(0)
                    .costMaintenance(BigDecimal.ZERO)
                    .maxDeposits(0)
                    .maxWithdrawal(0)
                    .state(1)
                    .build();
            productRulesRepository.save(rule).subscribe();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
