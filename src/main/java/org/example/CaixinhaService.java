package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.example.Caixinha.VALOR_MINIMO;

@Service
public class CaixinhaService {

    @Autowired
    private CaixinhaUtils caixinhaUtils;

    public List<CaixinhaResponse> calculaDitribuicaoInvestimento(BigDecimal valorSobrou) {
        List<CaixinhaResponse> answer = new ArrayList<>();
        List<Caixinha> caixinhas = caixinhaUtils.montaCaixinhas();
        BigDecimal totalSomaPontuacao = new BigDecimal(0);
        BigDecimal resto = new BigDecimal(0);
        BigDecimal soma = new BigDecimal(0);

        for (Caixinha caixinha : caixinhas) {
            caixinha.calculaPontuacao();
            totalSomaPontuacao = totalSomaPontuacao.add(caixinha.getPontuacao());
            soma = soma.add(caixinha.getTotal().subtract(caixinha.getArrecadado()));
        }

        if (valorSobrou.compareTo(soma) >= 0) {
            caixinhas.forEach(c -> c.setMensagem("PODE COMPRAR TUDO!"));
            return caixinhas.stream().map(CaixinhaMapper::toResponse).toList();
        }

        for (Caixinha caixinha : caixinhas) {
            caixinha.calculaPorcentagem(totalSomaPontuacao);
        }

        do {
            for (Caixinha caixinha : caixinhas) {
                BigDecimal investimentoParcial = caixinha.getPorcentagem().multiply(valorSobrou);
                BigDecimal investimentoFinal;
                if (investimentoParcial.compareTo(caixinha.getTotal().subtract(caixinha.getArrecadado().add(caixinha.getInvestimento()))) >= 0) {
                    investimentoFinal = caixinha.getTotal().subtract(caixinha.getArrecadado().add(caixinha.getInvestimento()));
                    resto = resto.add(investimentoParcial.subtract(investimentoFinal));
                    caixinha.setQuitada(true);
                } else {
                    investimentoFinal = investimentoParcial;
                }
                caixinha.adicionaInvestimento(investimentoFinal);
            }
            valorSobrou = resto;
            resto = new BigDecimal(0);
        } while (valorSobrou.compareTo(BigDecimal.valueOf(0.01)) >= 0);

        int controle = 128;
        while (controle > 0) {
            int utilidade = 4;
            while (utilidade > 0) {
                for (Caixinha c : caixinhas) {
                    if (c.getConfigCaixinha().getClassificacao().getValor() == controle
                            && c.getConfigCaixinha().getUtililidade().getValor() == utilidade) {
                        if (c.isQuitada()) {
                            c.setMensagem("Pode comprar!");
                        }
                        if (c.getInvestimento().compareTo(VALOR_MINIMO) < 0) {
                            c.setMensagem("Menor que valor programado!");
                        }
//                        else {
//                            c.setMensagem("Depositar: " + c.getInvestimento().subtract(VALOR_MINIMO).round(MathContext.DECIMAL32).setScale(2, RoundingMode.HALF_EVEN));
//                        }
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
