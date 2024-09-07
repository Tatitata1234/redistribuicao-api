package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaixinhaTesteService {

    @Autowired
    private CaixinhaUtils caixinhaUtils;

    public List<CaixinhaResponse> calculaDitribuicaoInvestimento(double valorSobrou) {
        CaixinhaDouble[] caixinhas = caixinhaUtils.montaCaixinhasDoubleArray();
        CaixinhaDouble[] temp = caixinhas;
        CaixinhaDouble[] tempDois = caixinhas;
        double soma = 0;
        double somaPontuacao = 0;

        double resto = valorSobrou;
        double restotemp = valorSobrou;

        for (int i = 0; i < caixinhas.length; i++) {
            soma = soma + temp[i].getTotal();
            temp[i].calculaPontuacao();
            somaPontuacao = somaPontuacao + temp[i].getPontuacao();
        }
        int ck = 1;
        do {
            for (CaixinhaDouble caixinhaDouble : temp) {
                double investimentoCalculado = caixinhaDouble.getPontuacao() / somaPontuacao * resto;
                double diferenca = caixinhaDouble.getTotal() - caixinhaDouble.getArrecadado() - caixinhaDouble.getInvestimento();
                if (investimentoCalculado >= diferenca) {
                    caixinhaDouble.adicionaInvestimento(diferenca);
                    tempDois = removeTempDois(caixinhaDouble, tempDois);
                    restotemp -= diferenca;
                    caixinhaDouble.setQuitada(true);
                    somaPontuacao -= caixinhaDouble.getPontuacao();
                } else {
                    caixinhaDouble.adicionaInvestimento(investimentoCalculado);
                    restotemp -= investimentoCalculado;
                }
            }
            System.out.println(ck++);
            resto = restotemp;
            temp = tempDois;
        } while (resto > 0.01);


        List<CaixinhaResponse> answer = new ArrayList<>();
        int controle = 128;
        while (controle > 0) {
            int utilidade = 4;
            while (utilidade > 0) {
                for (CaixinhaDouble c : caixinhas) {
                    if (c.getClassificacao().getValor() == controle
                            && c.getUtililidade().getValor() == utilidade) {
                        if (c.isQuitada()) {
                            c.setMensagem("Pode comprar!");
                        }
                        if (c.getInvestimento() < CaixinhaDouble.VALOR_MINIMO) {
                            c.setMensagem("Menor que valor programado!");
                        }
                        answer.add(CaixinhaMapper.toResponse(c));
                    }
                }
                utilidade /= 2;
            }
            controle /= 2;
        }
        return answer;
    }

    private CaixinhaDouble[] removeTempDois(CaixinhaDouble caixinhaDouble, CaixinhaDouble[] tempDois) {
        CaixinhaDouble[] answer = new CaixinhaDouble[tempDois.length-1];
        for (int i = 0, j=0; j < tempDois.length; j++) {
            if (tempDois[j] != null) {
                if (!tempDois[j].getNome().equals(caixinhaDouble.getNome())) {
                    answer[i] = tempDois[j];
                    i++;
                }
            }
        }
        return answer;
    }
}
