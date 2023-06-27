package com.bank.Enums;

public enum TypeClient {
    NATURAL(1),
    JURIDICA(2);

    private int numero;

    private TypeClient(int numero){
        this.numero=numero;
    }

    public int getNumero(){
        return numero;
    }
}
