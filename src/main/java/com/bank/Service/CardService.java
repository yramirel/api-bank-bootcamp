package com.bank.Service;

import com.bank.Model.Card;
import com.bank.Model.Client;
import com.bank.Model.Request.CardRequest;
import com.bank.Model.Request.ClientProductRequest;
import com.bank.Model.Request.ClientRequest;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

public interface CardService {
    public Maybe<Card> saveCard(CardRequest card);
    public Maybe<Card> saveCardAssociated(String cardNumber,ClientProductRequest clientProductRequest);
    public Maybe<Card> deleteCard(String cardNumber);
    public Maybe<Card> getCard(String cardNumber) throws Exception;
    public Flowable<Card> listCard(String cardNumber) throws Exception;
}
