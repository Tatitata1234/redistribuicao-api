package org.example.service.utils;

import org.example.controller.response.CaixinhaResponse;
import org.example.mapper.CaixinhaMapper;
import org.example.model.entity.Caixinha;
import org.example.model.entity.Classificacao;
import org.example.model.entity.Utilidade;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class CaixinhaUtils {
    public Caixinha[] montaCaixinhas() {
        Caixinha[] caixinhas = new Caixinha[26];
        caixinhas[0] = Caixinha.builder().nome("Viagem com mamis").total(new BigDecimal(20000)).arrecadado(BigDecimal.valueOf(244.61)).classificacao(Classificacao.builder().nome("VIAGEM").build()).utililidade(Utilidade.builder().nome("INUTIL").build()).build();
        caixinhas[1] = Caixinha.builder().nome("Urubici").total(new BigDecimal(2000)).arrecadado(BigDecimal.valueOf(60.80)).classificacao(Classificacao.builder().nome("VIAGEM").build()).utililidade(Utilidade.builder().nome("INUTIL").build()).build();
        caixinhas[2] = Caixinha.builder().nome("iPad").total(new BigDecimal(3500)).arrecadado(BigDecimal.valueOf(63.93)).classificacao(Classificacao.builder().nome("TECNOLOGIA").build()).utililidade(Utilidade.builder().nome("UTIL").build()).build();
        caixinhas[3] = Caixinha.builder().nome("Apartamento ENTRADA").total(BigDecimal.valueOf(11145.09)).arrecadado(BigDecimal.valueOf(147.04)).classificacao(Classificacao.builder().nome("DIVIDA").build()).utililidade(Utilidade.builder().nome("DIVIDA").build()).build();
        caixinhas[4] = Caixinha.builder().nome("Forno elétrico").total(new BigDecimal(700)).arrecadado(BigDecimal.valueOf(56.69 + 347)).classificacao(Classificacao.builder().nome("PRECISO_PARA_AGORA").build()).utililidade(Utilidade.builder().nome("UTIL").build()).build();
        caixinhas[5] = Caixinha.builder().nome("Studio Gabriel").total(new BigDecimal(50000)).arrecadado(BigDecimal.valueOf(37.04)).classificacao(Classificacao.builder().nome("QUERO").build()).utililidade(Utilidade.builder().nome("UTIL").build()).build();
        caixinhas[6] = Caixinha.builder().nome("Casamento").total(new BigDecimal(30000)).arrecadado(BigDecimal.valueOf(37.04)).classificacao(Classificacao.builder().nome("QUERO_MUITO").build()).utililidade(Utilidade.builder().nome("INUTIL").build()).build();
        caixinhas[7] = Caixinha.builder().nome("Sushi Cléber").total(new BigDecimal(600)).arrecadado(BigDecimal.valueOf(85.15)).classificacao(Classificacao.builder().nome("QUERO_MUITO").build()).utililidade(Utilidade.builder().nome("INUTIL").build()).build();
        caixinhas[8] = Caixinha.builder().nome("Presente de Natal amoreco").total(new BigDecimal(400)).arrecadado(BigDecimal.valueOf(111.66)).classificacao(Classificacao.builder().nome("QUERO_MUITO").build()).utililidade(Utilidade.builder().nome("INUTIL").build()).build();
        caixinhas[9] = Caixinha.builder().nome("Quebra-cabeça").total(new BigDecimal(150)).arrecadado(BigDecimal.valueOf(45.30)).classificacao(Classificacao.builder().nome("BUGIGANGA").build()).utililidade(Utilidade.builder().nome("INUTIL").build()).build();
        caixinhas[10] = Caixinha.builder().nome("Rummikub").total(new BigDecimal(200)).arrecadado(BigDecimal.valueOf(41.97)).classificacao(Classificacao.builder().nome("BUGIGANGA").build()).utililidade(Utilidade.builder().nome("INUTIL").build()).build();
        caixinhas[11] = Caixinha.builder().nome("Fogão").total(new BigDecimal(1500)).arrecadado(BigDecimal.valueOf(199.36)).classificacao(Classificacao.builder().nome("PRECISO_PARA_AGORA").build()).utililidade(Utilidade.builder().nome("UTIL").build()).build();
        caixinhas[12] = Caixinha.builder().nome("Máquina de lavar de roupa").total(new BigDecimal(3000)).arrecadado(BigDecimal.valueOf(100.21)).classificacao(Classificacao.builder().nome("PRECISO_PARA_AGORA").build()).utililidade(Utilidade.builder().nome("UTIL").build()).build();
        caixinhas[13] = Caixinha.builder().nome("Geladeira").total(new BigDecimal(3000)).arrecadado(BigDecimal.valueOf(100.20)).classificacao(Classificacao.builder().nome("PRECISO_PARA_AGORA").build()).utililidade(Utilidade.builder().nome("UTIL").build()).build();
        caixinhas[14] = Caixinha.builder().nome("Kit elemento químico").total(new BigDecimal(200)).arrecadado(BigDecimal.valueOf(27.17)).classificacao(Classificacao.builder().nome("BUGIGANGA").build()).utililidade(Utilidade.builder().nome("UTIL").build()).build();
        caixinhas[15] = Caixinha.builder().nome("Aspirador").total(new BigDecimal(1000)).arrecadado(BigDecimal.valueOf(255.84)).classificacao(Classificacao.builder().nome("PRECISO_PARA_AGORA").build()).utililidade(Utilidade.builder().nome("UTIL").build()).build();
        caixinhas[16] = Caixinha.builder().nome("The sims").total(new BigDecimal(200)).arrecadado(BigDecimal.valueOf(80.66)).classificacao(Classificacao.builder().nome("BUGIGANGA").build()).utililidade(Utilidade.builder().nome("INUTIL").build()).build();
        caixinhas[17] = Caixinha.builder().nome("Monitor").total(new BigDecimal(12000)).arrecadado(BigDecimal.valueOf(6.02)).classificacao(Classificacao.builder().nome("TECNOLOGIA").build()).utililidade(Utilidade.builder().nome("UTIL").build()).build();
        caixinhas[18] = Caixinha.builder().nome("Apple Watch").total(new BigDecimal(4500)).arrecadado(BigDecimal.valueOf(6.02)).classificacao(Classificacao.builder().nome("TECNOLOGIA").build()).utililidade(Utilidade.builder().nome("UTIL").build()).build();
        caixinhas[19] = Caixinha.builder().nome("Tramontina itria").total(new BigDecimal(850)).arrecadado(BigDecimal.valueOf(75.96)).classificacao(Classificacao.builder().nome("QUERO_MUITO").build()).utililidade(Utilidade.builder().nome("UTIL").build()).build();
        caixinhas[20] = Caixinha.builder().nome("ZFOLD").total(new BigDecimal(11000)).arrecadado(BigDecimal.valueOf(6.01)).classificacao(Classificacao.builder().nome("TECNOLOGIA").build()).utililidade(Utilidade.builder().nome("UTIL").build()).build();
        caixinhas[21] = Caixinha.builder().nome("Lava louça").total(new BigDecimal(2000)).arrecadado(BigDecimal.valueOf(127.40)).classificacao(Classificacao.builder().nome("PRECISO_PARA_AGORA").build()).utililidade(Utilidade.builder().nome("UTIL").build()).build();
        caixinhas[22] = Caixinha.builder().nome("Pagar pai").total(new BigDecimal(4000)).arrecadado(BigDecimal.valueOf(254.82)).classificacao(Classificacao.builder().nome("DIVIDA").build()).utililidade(Utilidade.builder().nome("DIVIDA").build()).build();
        caixinhas[23] = Caixinha.builder().nome("Apartamento QUITAR").total(new BigDecimal(168000)).arrecadado(BigDecimal.valueOf(6.07)).classificacao(Classificacao.builder().nome("DIVIDA").build()).utililidade(Utilidade.builder().nome("DIVIDA").build()).build();
        caixinhas[24] = Caixinha.builder().nome("Faxineira").total(new BigDecimal(20000)).arrecadado(new BigDecimal(6)).classificacao(Classificacao.builder().nome("QUERO_MUITO").build()).utililidade(Utilidade.builder().nome("UTIL").build()).build();
        caixinhas[25] = Caixinha.builder().nome("Emergência Carro").total(new BigDecimal(4000)).arrecadado(new BigDecimal(1)).classificacao(Classificacao.builder().nome("PRECISO").build()).utililidade(Utilidade.builder().nome("UTIL").build()).build();
        return caixinhas;
    }

    public static List<CaixinhaResponse> ordenaCaixinhas(Caixinha[] caixinhas) {
        List<CaixinhaResponse> answer = new ArrayList<>();
        int controle = 128;
        while (controle > 0) {
            int utilidade = 4;
            while (utilidade > 0) {
                for (Caixinha c : caixinhas) {
                    if (c.getClassificacao().getValor() == controle
                            && c.getUtililidade().getValor() == utilidade) {
                        c.setMensagem();
                        answer.add(CaixinhaMapper.toResponse(c));
                    }
                }
                utilidade /= 2;
            }
            controle /= 2;
        }
        return answer;
    }
}
