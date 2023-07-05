package com.bank.Repository;

import com.bank.Model.Card;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.RxJava3SortingRepository;


public interface CardRepository extends RxJava3SortingRepository<Card,String> {
    Maybe<Card> getByCardNumber(String cardNumber);

    @Query("{state:1}")
    Flowable<Card> getAllCard();
}
