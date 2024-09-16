package org.example.service;

import org.example.controller.request.CaixinhaRequest;
import org.example.model.Caixinha;
import org.example.mapper.CaixinhaMapper;
import org.example.controller.response.CaixinhaResponse;
import org.example.service.utils.CaixinhaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.model.Caixinha.VALOR_MINIMO;

@Service
public class CaixinhaService {

    @Autowired
    private CaixinhaUtils caixinhaUtils;



    public List<CaixinhaResponse> calculaDitribuicaoInvestimento(BigDecimal valorSobrou) {
        Caixinha[] caixinhas = caixinhaUtils.montaCaixinhas();
        Caixinha[] temp = caixinhas;
        Caixinha[] tempDois = caixinhas;

        BigDecimal resto = valorSobrou;
        BigDecimal restoTemp = valorSobrou;
        BigDecimal soma = new BigDecimal(0);

        BigDecimal totalSomaPontuacao = new BigDecimal(0);

        for (Caixinha c : caixinhas) {
            c.calculaPontuacao();
            totalSomaPontuacao = totalSomaPontuacao.add(c.getPontuacao());
            soma = soma.add(c.getTotal().subtract(c.getArrecadado()));
        }

        int ck = 1;

        if (valorSobrou.compareTo(soma) >= 0) {
            Arrays.stream(caixinhas).toList().forEach(c -> {
                c.setMensagem("PODE COMPRAR TUDO!");
                c.setQuitada(true);
                c.setInvestimento(c.getTotal().subtract(c.getArrecadado()));
            });
            return Arrays.stream(caixinhas).toList().stream().map(CaixinhaMapper::toResponse).toList();
        }

        do {
            for (Caixinha c : temp) {
                BigDecimal investimentoCalculado = c.getPontuacao().divide(totalSomaPontuacao, MathContext.DECIMAL128).multiply(resto);
                BigDecimal diferenca = c.getTotal().subtract(c.getArrecadado()).subtract(c.getInvestimento());
                if (investimentoCalculado.compareTo(diferenca) >= 0) {
                    c.adicionaInvestimento(diferenca);
                    tempDois = removeTempDois(c, tempDois);
                    restoTemp = restoTemp.subtract(diferenca);
                    c.setQuitada(true);
                    totalSomaPontuacao = totalSomaPontuacao.subtract(c.getPontuacao());
                } else {
                    c.adicionaInvestimento(investimentoCalculado);
                    restoTemp = restoTemp.subtract(investimentoCalculado);
                }
            }
            ck++;
            resto = restoTemp;
            temp = tempDois;
        } while (resto.compareTo(BigDecimal.valueOf(0.01)) >= 0);

        System.out.println(ck);

        return ordenaCaixinhas(caixinhas);
    }

    private static List<CaixinhaResponse> ordenaCaixinhas(Caixinha[] caixinhas) {
        List<CaixinhaResponse> answer = new ArrayList<>();
        int controle = 128;
        while (controle > 0) {
            int utilidade = 4;
            while (utilidade > 0) {
                for (Caixinha c : caixinhas) {
                    if (c.getClassificacao().getValor() == controle
                            && c.getUtililidade().getValor() == utilidade) {
                        setMensagem(c);
                        answer.add(CaixinhaMapper.toResponse(c));
                    }
                }
                utilidade /= 2;
            }
            controle /= 2;
        }
        return answer;
    }

    private static void setMensagem(Caixinha c) {
        if (c.isQuitada()) {
            c.setMensagem("Pode comprar!");
        }
        if (c.getInvestimento().compareTo(VALOR_MINIMO) < 0) {
            c.setMensagem("Menor que valor programado!");
        }
    }

    private Caixinha[] removeTempDois(Caixinha c, Caixinha[] tempDois) {
        Caixinha[] answer = new Caixinha[tempDois.length - 1];
        int i = 0;
        for (Caixinha dois : tempDois) {
            if (dois != null && (!dois.getNome().equals(c.getNome()))) {
                answer[i] = dois;
                i++;
            }
        }
        return answer;
    }

    public List<CaixinhaResponse> calculaDitribuicaoInvestimento(BigDecimal valorSobrou, List<CaixinhaRequest> caixinhas) {
        Caixinha[] caixinhasArray = caixinhas.stream().map(CaixinhaMapper::toEntity).toList().toArray(Caixinha[]::new);
        Caixinha[] temp = caixinhasArray;
        Caixinha[] tempDois = caixinhasArray;

        BigDecimal resto = valorSobrou;
        BigDecimal restoTemp = valorSobrou;

        BigDecimal soma = new BigDecimal(0);
        BigDecimal totalSomaPontuacao = new BigDecimal(0);

        for (Caixinha c : caixinhasArray) {
            c.calculaPontuacao();
            totalSomaPontuacao = totalSomaPontuacao.add(c.getPontuacao());
            soma = soma.add(c.getTotal().subtract(c.getArrecadado()));
        }
        int ck = 1;

        if (valorSobrou.compareTo(soma) >= 0) {
            Arrays.stream(caixinhasArray).toList().forEach(c -> {
                c.setMensagem("PODE COMPRAR TUDO!");
                c.setQuitada(true);
                c.setInvestimento(c.getTotal().subtract(c.getArrecadado()));
            });
            return Arrays.stream(caixinhasArray).toList().stream().map(CaixinhaMapper::toResponse).toList();
        }

        BigDecimal totalSomaPontuacaoTemp = totalSomaPontuacao;
        do {
            for (Caixinha c : temp) {
                if (c.isQuitada()) continue;
                BigDecimal investimentoCalculado = c.getPontuacao().divide(totalSomaPontuacao, MathContext.DECIMAL128).multiply(resto);
                BigDecimal diferenca = c.getTotal().subtract(c.getArrecadado()).subtract(c.getInvestimento());
                if (investimentoCalculado.compareTo(diferenca) >= 0) {
                    c.adicionaInvestimento(diferenca);
                    tempDois = removeTempDois(c, tempDois);
                    restoTemp = restoTemp.subtract(diferenca);
                    c.setQuitada(true);
                    totalSomaPontuacaoTemp = totalSomaPontuacao.subtract(c.getPontuacao());
                } else {
                    c.adicionaInvestimento(investimentoCalculado);
                    restoTemp = restoTemp.subtract(investimentoCalculado);
                }
            }
            ck++;
            resto = restoTemp;
            temp = tempDois;
            totalSomaPontuacao = totalSomaPontuacaoTemp;
        } while (resto.compareTo(BigDecimal.valueOf(0.01)) >= 0);

        System.out.println(ck);

        return ordenaCaixinhas(caixinhasArray);
    }
}
