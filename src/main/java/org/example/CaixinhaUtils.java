package org.example;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CaixinhaUtils {
    public List<Caixinha> montaCaixinhas() {
        List<Caixinha> caixinhas = new ArrayList<>();
        caixinhas.add(Caixinha.builder().nome("Viagem com mamis").total(new BigDecimal(20000)).arrecadado(BigDecimal.valueOf(244.29))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.VIAGEM).utililidade(Utililidade.INUTIL).build()).build());
        caixinhas.add(Caixinha.builder().nome("Urubici").total(new BigDecimal(2000)).arrecadado(BigDecimal.valueOf(60.72))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.VIAGEM).utililidade(Utililidade.INUTIL).build()).build());
        caixinhas.add(Caixinha.builder().nome("iPad").total(new BigDecimal(3500)).arrecadado(BigDecimal.valueOf(63.84))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.TECNOLOGIA).utililidade(Utililidade.UTIL).build()).build());
        caixinhas.add(Caixinha.builder().nome("Apartamento ENTRADA").total(BigDecimal.valueOf(11145.09)).arrecadado(BigDecimal.valueOf(60.52))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.DIVIDA).utililidade(Utililidade.DIVIDA).build()).build());
        caixinhas.add(Caixinha.builder().nome("Forno elétrico").total(new BigDecimal(700)).arrecadado(BigDecimal.valueOf(56.58))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.PRECISO_PARA_AGORA).utililidade(Utililidade.UTIL).build()).build());
        caixinhas.add(Caixinha.builder().nome("Studio Gabriel").total(new BigDecimal(50000)).arrecadado(BigDecimal.valueOf(36.98))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.QUERO).utililidade(Utililidade.UTIL).build()).build());
        caixinhas.add(Caixinha.builder().nome("Casamento").total(new BigDecimal(30000)).arrecadado(BigDecimal.valueOf(36.98))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.QUERO_MUITO).utililidade(Utililidade.INUTIL).build()).build());
        caixinhas.add(Caixinha.builder().nome("Sushi Cléber").total(new BigDecimal(600)).arrecadado(BigDecimal.valueOf(36.98))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.QUERO_MUITO).utililidade(Utililidade.INUTIL).build()).build());
        caixinhas.add(Caixinha.builder().nome("Presente de Natal amoreco").total(new BigDecimal(400)).arrecadado(BigDecimal.valueOf(36.98))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.QUERO_MUITO).utililidade(Utililidade.INUTIL).build()).build());
        caixinhas.add(Caixinha.builder().nome("Quebra-cabeça").total(new BigDecimal(150)).arrecadado(BigDecimal.valueOf(36.98))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.BUGIGANGA).utililidade(Utililidade.INUTIL).build()).build());
        caixinhas.add(Caixinha.builder().nome("Pantys").total(new BigDecimal(100)).arrecadado(BigDecimal.valueOf(36.98))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.PRECISO).utililidade(Utililidade.UTIL).build()).build());
        caixinhas.add(Caixinha.builder().nome("Rummikub").total(new BigDecimal(200)).arrecadado(BigDecimal.valueOf(36.97))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.BUGIGANGA).utililidade(Utililidade.INUTIL).build()).build());
        caixinhas.add(Caixinha.builder().nome("Fogão").total(new BigDecimal(1500)).arrecadado(BigDecimal.valueOf(41.44))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.PRECISO_PARA_AGORA).utililidade(Utililidade.UTIL).build()).build());
        caixinhas.add(Caixinha.builder().nome("Carteira").total(new BigDecimal(80)).arrecadado(BigDecimal.valueOf(26.48))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.QUERO).utililidade(Utililidade.UTIL).build()).build());
        caixinhas.add(Caixinha.builder().nome("Máquina de lavar de roupa").total(new BigDecimal(3000)).arrecadado(BigDecimal.valueOf(20.25))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.PRECISO_PARA_AGORA).utililidade(Utililidade.UTIL).build()).build());
        caixinhas.add(Caixinha.builder().nome("Geladeira").total(new BigDecimal(3000)).arrecadado(BigDecimal.valueOf(20.25))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.PRECISO_PARA_AGORA).utililidade(Utililidade.UTIL).build()).build());
        caixinhas.add(Caixinha.builder().nome("Kit elemento químico").total(new BigDecimal(200)).arrecadado(BigDecimal.valueOf(12.24))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.BUGIGANGA).utililidade(Utililidade.UTIL).build()).build());
        caixinhas.add(Caixinha.builder().nome("Aspirador").total(new BigDecimal(1000)).arrecadado(new BigDecimal(6))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.PRECISO_PARA_AGORA).utililidade(Utililidade.UTIL).build()).build());
        caixinhas.add(Caixinha.builder().nome("The sims").total(new BigDecimal(200)).arrecadado(new BigDecimal(17))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.QUERO).utililidade(Utililidade.INUTIL).build()).build());
        caixinhas.add(Caixinha.builder().nome("Monitor").total(new BigDecimal(12000)).arrecadado(new BigDecimal(6))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.TECNOLOGIA).utililidade(Utililidade.UTIL).build()).build());
        caixinhas.add(Caixinha.builder().nome("Apple Watch").total(new BigDecimal(4500)).arrecadado(new BigDecimal(6))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.TECNOLOGIA).utililidade(Utililidade.UTIL).build()).build());
        caixinhas.add(Caixinha.builder().nome("Tramontina itria").total(new BigDecimal(850)).arrecadado(new BigDecimal(6))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.QUERO_MUITO).utililidade(Utililidade.UTIL).build()).build());
        caixinhas.add(Caixinha.builder().nome("ZFOLD").total(new BigDecimal(11000)).arrecadado(new BigDecimal(6))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.TECNOLOGIA).utililidade(Utililidade.UTIL).build()).build());
        caixinhas.add(Caixinha.builder().nome("Lava louça").total(new BigDecimal(2000)).arrecadado(new BigDecimal(1))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.PRECISO_PARA_AGORA).utililidade(Utililidade.UTIL).build()).build());
        caixinhas.add(Caixinha.builder().nome("Pagar pai").total(new BigDecimal(4000)).arrecadado(new BigDecimal(1))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.DIVIDA).utililidade(Utililidade.DIVIDA).build()).build());
        caixinhas.add(Caixinha.builder().nome("Apartamento QUITAR").total(new BigDecimal(168000)).arrecadado(new BigDecimal(1))
                .configCaixinha(ConfigCaixinha.builder().classificacao(Classificacao.DIVIDA).utililidade(Utililidade.DIVIDA).build()).build());
        return caixinhas;
    }
}
