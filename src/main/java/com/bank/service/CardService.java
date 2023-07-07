package com.bank.service;

import com.bank.model.Card;
import com.bank.model.request.CardRequest;
import com.bank.model.request.ClientProductRequest;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

/**
 * CardService Interface fro Card.
 */
public interface CardService {
  public Maybe<Card> saveCard(CardRequest card);

  public Maybe<Card> saveCardAssociated(String cardNumber,
                                        ClientProductRequest clientProductRequest);

  public Maybe<Card> deleteCard(String cardNumber);

  public Maybe<Card> getCard(String cardNumber) throws Exception;

  public Flowable<Card> listCard(String cardNumber) throws Exception;
}
