package com.bank.Service.Impl;

import com.bank.ErrorHandler.ConflictException;
import com.bank.Model.Card;
import com.bank.Model.ClientProduct;
import com.bank.Model.Request.CardRequest;
import com.bank.Model.Request.ClientProductRequest;
import com.bank.Repository.CardRepository;
import com.bank.Repository.ClientProductRepository;
import com.bank.Service.CardService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardImpl implements CardService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private ClientProductRepository clientProductRepository;

    @Override
    public Maybe<Card> saveCard(CardRequest card){
        return cardRepository.getByCardNumber(card.getCardNumber()).isEmpty()
                .flatMap(isEmpty->{
                    card.setState(1);
                    return isEmpty?cardRepository.save(card.toCard())
                            :Single.error(new ConflictException("El La tarjeta ya Existe"));
                }).toMaybe();
    }
    @Override
    public Maybe<Card> saveCardAssociated(String cardNumber,ClientProductRequest clientProductRequest){
        return cardRepository.getByCardNumber(cardNumber)
                .switchIfEmpty(Maybe.error(new ConflictException("El La tarjeta No Existe")))
                .flatMap(card->{
                    ClientProduct clientProduct=clientProductRepository.getByAccountNumber(clientProductRequest.getAccountNumber())
                            .switchIfEmpty(Maybe.error(new ConflictException("La Cuenta No Existe"))).blockingGet();
                    List<ClientProduct> associatedAccounts = card.getAssociatedAccounts();
                    associatedAccounts=associatedAccounts==null?new ArrayList<>():associatedAccounts;
                    associatedAccounts.add(clientProduct);
                    cardRepository.save(card).subscribe();
                    return Maybe.just(card);
                });
    }
    @Override
    public Maybe<Card> deleteCard(String CardNumber){
        return cardRepository.getByCardNumber(CardNumber).toSingle()
                .flatMap(card->{
                    card.setState(0);
                    return cardRepository.save(card);
                }).toMaybe();
    }
    @Override
    public Maybe<Card> getCard(String CardNumber) throws Exception{
        return this.cardRepository.getByCardNumber(CardNumber);
    }

    @Override
    public Flowable<Card> listCard(String CardNumber) throws Exception{
        return this.cardRepository.getAllCard();
    }
}
