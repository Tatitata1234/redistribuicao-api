package org.example;

import lombok.Getter;

@Getter
public enum Utililidade {

    DIVIDA(4),UTIL(2), INUTIL(1);

    private int valor;


    Utililidade(int i) {
        this.valor = i;
    }
}
