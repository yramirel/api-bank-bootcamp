package com.bank.service.impl;

import com.bank.model.Product;
import com.bank.model.ProductRules;
import com.bank.repository.ProductRepository;
import com.bank.repository.ProductRulesRepository;
import com.bank.service.ProductRulesService;
import io.reactivex.rxjava3.core.Maybe;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProductRulesServiceImpl Class for rules.
 */
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

      ProductRules rule = ProductRules.builder()
                              .typeClient(1)
                              .codeProduct("")
                              .maxAccount(0)
                              .costMaintenance(BigDecimal.ZERO)
                              .maxDeposits(0)
                              .maxWithdrawal(0)
                              .state(1)
                              .build();
      productRulesRepository.save(rule).subscribe();

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
