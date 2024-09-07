package org.example;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static org.example.Classificacao.*;
import static org.example.Utililidade.INUTIL;
import static org.example.Utililidade.UTIL;

@Service
public class CaixinhaUtils {
    public Caixinha[] montaCaixinhas() {
        Caixinha[] caixinhas = new Caixinha[26];
        caixinhas[0] =  Caixinha.builder().nome("Viagem com mamis").total(new BigDecimal(20000)).arrecadado(BigDecimal.valueOf(244.61)).classificacao(VIAGEM).utililidade(INUTIL).build();
        caixinhas[1] =  Caixinha.builder().nome("Urubici").total(new BigDecimal(2000)).arrecadado(BigDecimal.valueOf(60.80)).classificacao(VIAGEM).utililidade(INUTIL).build();
        caixinhas[2] =  Caixinha.builder().nome("iPad").total(new BigDecimal(3500)).arrecadado(BigDecimal.valueOf(63.93)).classificacao(TECNOLOGIA).utililidade(UTIL).build();
        caixinhas[3] =  Caixinha.builder().nome("Apartamento ENTRADA").total(BigDecimal.valueOf(11145.09)).arrecadado(BigDecimal.valueOf(147.04)).classificacao(DIVIDA).utililidade(Utililidade.DIVIDA).build();
        caixinhas[4] =  Caixinha.builder().nome("Forno elétrico").total(new BigDecimal(700)).arrecadado(BigDecimal.valueOf(56.69+347)).classificacao(PRECISO_PARA_AGORA).utililidade(UTIL).build();
        caixinhas[5] =  Caixinha.builder().nome("Studio Gabriel").total(new BigDecimal(50000)).arrecadado(BigDecimal.valueOf(37.04)).classificacao(QUERO).utililidade(UTIL).build();
        caixinhas[6] =  Caixinha.builder().nome("Casamento").total(new BigDecimal(30000)).arrecadado(BigDecimal.valueOf(37.04)).classificacao(QUERO_MUITO).utililidade(INUTIL).build();
        caixinhas[7] =  Caixinha.builder().nome("Sushi Cléber").total(new BigDecimal(600)).arrecadado(BigDecimal.valueOf(85.15)).classificacao(QUERO_MUITO).utililidade(INUTIL).build();
        caixinhas[8] =  Caixinha.builder().nome("Presente de Natal amoreco").total(new BigDecimal(400)).arrecadado(BigDecimal.valueOf(111.66)).classificacao(QUERO_MUITO).utililidade(INUTIL).build();
        caixinhas[9] =  Caixinha.builder().nome("Quebra-cabeça").total(new BigDecimal(150)).arrecadado(BigDecimal.valueOf(45.30)).classificacao(BUGIGANGA).utililidade(INUTIL).build();
        caixinhas[10] = Caixinha.builder().nome("Rummikub").total(new BigDecimal(200)).arrecadado(BigDecimal.valueOf(41.97)).classificacao(BUGIGANGA).utililidade(INUTIL).build();
        caixinhas[11] = Caixinha.builder().nome("Fogão").total(new BigDecimal(1500)).arrecadado(BigDecimal.valueOf(199.36)).classificacao(PRECISO_PARA_AGORA).utililidade(UTIL).build();
        caixinhas[12] = Caixinha.builder().nome("Máquina de lavar de roupa").total(new BigDecimal(3000)).arrecadado(BigDecimal.valueOf(100.21)).classificacao(PRECISO_PARA_AGORA).utililidade(UTIL).build();
        caixinhas[13] = Caixinha.builder().nome("Geladeira").total(new BigDecimal(3000)).arrecadado(BigDecimal.valueOf(100.20)).classificacao(PRECISO_PARA_AGORA).utililidade(UTIL).build();
        caixinhas[14] = Caixinha.builder().nome("Kit elemento químico").total(new BigDecimal(200)).arrecadado(BigDecimal.valueOf(27.17)).classificacao(BUGIGANGA).utililidade(UTIL).build();
        caixinhas[15] = Caixinha.builder().nome("Aspirador").total(new BigDecimal(1000)).arrecadado(BigDecimal.valueOf(255.84)).classificacao(PRECISO_PARA_AGORA).utililidade(UTIL).build();
        caixinhas[16] = Caixinha.builder().nome("The sims").total(new BigDecimal(200)).arrecadado(BigDecimal.valueOf(80.66)).classificacao(BUGIGANGA).utililidade(INUTIL).build();
        caixinhas[17] = Caixinha.builder().nome("Monitor").total(new BigDecimal(12000)).arrecadado(BigDecimal.valueOf(6.02)).classificacao(TECNOLOGIA).utililidade(UTIL).build();
        caixinhas[18] = Caixinha.builder().nome("Apple Watch").total(new BigDecimal(4500)).arrecadado(BigDecimal.valueOf(6.02)).classificacao(TECNOLOGIA).utililidade(UTIL).build();
        caixinhas[19] = Caixinha.builder().nome("Tramontina itria").total(new BigDecimal(850)).arrecadado(BigDecimal.valueOf(75.96)).classificacao(QUERO_MUITO).utililidade(UTIL).build();
        caixinhas[20] = Caixinha.builder().nome("ZFOLD").total(new BigDecimal(11000)).arrecadado(BigDecimal.valueOf(6.01)).classificacao(TECNOLOGIA).utililidade(UTIL).build();
        caixinhas[21] = Caixinha.builder().nome("Lava louça").total(new BigDecimal(2000)).arrecadado(BigDecimal.valueOf(127.40)).classificacao(PRECISO_PARA_AGORA).utililidade(UTIL).build();
        caixinhas[22] = Caixinha.builder().nome("Pagar pai").total(new BigDecimal(4000)).arrecadado(BigDecimal.valueOf(254.82)).classificacao(DIVIDA).utililidade(Utililidade.DIVIDA).build();
        caixinhas[23] = Caixinha.builder().nome("Apartamento QUITAR").total(new BigDecimal(168000)).arrecadado(BigDecimal.valueOf(6.07)).classificacao(DIVIDA).utililidade(Utililidade.DIVIDA).build();
        caixinhas[24] = Caixinha.builder().nome("Faxineira").total(new BigDecimal(20000)).arrecadado(new BigDecimal(6)).classificacao(QUERO_MUITO).utililidade(UTIL).build();
        caixinhas[25] = Caixinha.builder().nome("Emergência Carro").total(new BigDecimal(4000)).arrecadado(new BigDecimal(1)).classificacao(PRECISO).utililidade(UTIL).build();
        return caixinhas;
    }

    public CaixinhaDouble[] montaCaixinhasDoubleArray() {
        CaixinhaDouble[] caixinhas = new CaixinhaDouble[26];
        caixinhas[0] = CaixinhaDouble.builder().nome("Viagem com mamis").total(20000).arrecadado(244.61).classificacao(Classificacao.VIAGEM).utililidade(INUTIL).build();
        caixinhas[1] = CaixinhaDouble.builder().nome("Urubici").total(2000).arrecadado(60.80).classificacao(Classificacao.VIAGEM).utililidade(INUTIL).build();
        caixinhas[2] = CaixinhaDouble.builder().nome("iPad").total(3500).arrecadado(63.93).classificacao(Classificacao.TECNOLOGIA).utililidade(UTIL).build();
        caixinhas[3] = CaixinhaDouble.builder().nome("Apartamento ENTRADA").total(11145.09).arrecadado(147.04).classificacao(Classificacao.DIVIDA).utililidade(Utililidade.DIVIDA).build();
        caixinhas[4] = CaixinhaDouble.builder().nome("Forno elétrico").total(700).arrecadado(56.69+347).classificacao(PRECISO_PARA_AGORA).utililidade(UTIL).build();
        caixinhas[5] = CaixinhaDouble.builder().nome("Studio Gabriel").total(50000).arrecadado(37.04).classificacao(Classificacao.QUERO).utililidade(UTIL).build();
        caixinhas[6] = CaixinhaDouble.builder().nome("Casamento").total(30000).arrecadado(37.04).classificacao(QUERO_MUITO).utililidade(INUTIL).build();
        caixinhas[7] = CaixinhaDouble.builder().nome("Sushi Cléber").total(600).arrecadado(85.15).classificacao(QUERO_MUITO).utililidade(INUTIL).build();
        caixinhas[8] = CaixinhaDouble.builder().nome("Presente de Natal amoreco").total(400).arrecadado(111.66).classificacao(QUERO_MUITO).utililidade(INUTIL).build();
        caixinhas[9] = CaixinhaDouble.builder().nome("Quebra-cabeça").total(150).arrecadado(45.30).classificacao(BUGIGANGA).utililidade(INUTIL).build();
        caixinhas[10] = CaixinhaDouble.builder().nome("Rummikub").total(200).arrecadado(41.97).classificacao(BUGIGANGA).utililidade(INUTIL).build();
        caixinhas[11] = CaixinhaDouble.builder().nome("Fogão").total(1500).arrecadado(199.36).classificacao(PRECISO_PARA_AGORA).utililidade(UTIL).build();
        caixinhas[12] = CaixinhaDouble.builder().nome("Máquina de lavar de roupa").total(3000).arrecadado(100.21).classificacao(PRECISO_PARA_AGORA).utililidade(UTIL).build();
        caixinhas[13] = CaixinhaDouble.builder().nome("Geladeira").total(3000).arrecadado(100.20).classificacao(PRECISO_PARA_AGORA).utililidade(UTIL).build();
        caixinhas[14] = CaixinhaDouble.builder().nome("Kit elemento químico").total(200).arrecadado(27.17).classificacao(BUGIGANGA).utililidade(UTIL).build();
        caixinhas[15] = CaixinhaDouble.builder().nome("Aspirador").total(1000).arrecadado(255.84).classificacao(PRECISO_PARA_AGORA).utililidade(UTIL).build();
        caixinhas[16] = CaixinhaDouble.builder().nome("The sims").total(200).arrecadado(80.66).classificacao(Classificacao.QUERO).utililidade(INUTIL).build();
        caixinhas[17] = CaixinhaDouble.builder().nome("Monitor").total(12000).arrecadado(6.02).classificacao(Classificacao.TECNOLOGIA).utililidade(UTIL).build();
        caixinhas[18] = CaixinhaDouble.builder().nome("Apple Watch").total(4500).arrecadado(6.02).classificacao(Classificacao.TECNOLOGIA).utililidade(UTIL).build();
        caixinhas[19] = CaixinhaDouble.builder().nome("Tramontina itria").total(850).arrecadado(75.96).classificacao(QUERO_MUITO).utililidade(UTIL).build();
        caixinhas[20] = CaixinhaDouble.builder().nome("ZFOLD").total(11000).arrecadado(6.01).classificacao(Classificacao.TECNOLOGIA).utililidade(UTIL).build();
        caixinhas[21] = CaixinhaDouble.builder().nome("Lava louça").total(2000).arrecadado(127.40).classificacao(PRECISO_PARA_AGORA).utililidade(UTIL).build();
        caixinhas[22] = CaixinhaDouble.builder().nome("Pagar pai").total(4000).arrecadado(254.82).classificacao(Classificacao.DIVIDA).utililidade(Utililidade.DIVIDA).build();
        caixinhas[23] = CaixinhaDouble.builder().nome("Apartamento QUITAR").total(168000).arrecadado(6.07).classificacao(Classificacao.DIVIDA).utililidade(Utililidade.DIVIDA).build();
        caixinhas[24] = CaixinhaDouble.builder().nome("Faxineira").total(20000).arrecadado(6).classificacao(QUERO_MUITO).utililidade(UTIL).build();
        caixinhas[25] = CaixinhaDouble.builder().nome("Emergência Carro").total(4000).arrecadado(1).classificacao(Classificacao.PRECISO).utililidade(UTIL).build();
        return caixinhas;
    }
}
