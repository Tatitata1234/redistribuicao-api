package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum Classificacao {

    DIVIDA(128),PRECISO_PARA_AGORA(64), PRECISO(32), QUERO_MUITO(16), QUERO(8), VIAGEM(4), TECNOLOGIA(2), BUGIGANGA(1);

    private int valor;

    Classificacao(int i) {
        this.valor = i;
    }
}
