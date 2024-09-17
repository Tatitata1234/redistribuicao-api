package org.example.service;

import org.example.controller.request.CaixinhaRequest;
import org.example.controller.response.CaixinhaResponse;
import org.example.mapper.CaixinhaMapper;
import org.example.model.dto.SomaPontuacaoDTO;
import org.example.model.entity.Caixinha;
import org.example.service.utils.CaixinhaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.List;

import static org.example.service.utils.CaixinhaUtils.ordenaCaixinhas;

@Service
public class CaixinhaService {

    @Autowired
    private CaixinhaUtils caixinhaUtils;

    @Cacheable("caixinha")
    public List<CaixinhaResponse> calculaDitribuicaoInvestimento(BigDecimal valorSobrou) {
        Caixinha[] caixinhas = caixinhaUtils.montaCaixinhas();

        return doRedistribuicao(valorSobrou, caixinhas);
    }

    @Cacheable("caixinha")
    public List<CaixinhaResponse> calculaDitribuicaoInvestimento(BigDecimal valorSobrou, List<CaixinhaRequest> caixinhas) {
        Caixinha[] caixinhasArray = caixinhas.stream().map(CaixinhaMapper::toEntity).toList().toArray(Caixinha[]::new);

        return doRedistribuicao(valorSobrou, caixinhasArray);
    }


    private List<CaixinhaResponse> doRedistribuicao(BigDecimal valorSobrou, Caixinha[] caixinhasArray) {
        SomaPontuacaoDTO somaPontuacaoDTO = calculaSomaPontuacao(caixinhasArray);

        BigDecimal soma = somaPontuacaoDTO.getSoma();
        BigDecimal totalSomaPontuacao = somaPontuacaoDTO.getTotalSomaPontuacao();

        if (valorSobrou.compareTo(soma) >= 0) {
            Arrays.stream(caixinhasArray).toList().forEach(CaixinhaMapper::toQuitada);
            return Arrays.stream(caixinhasArray).toList().stream().map(CaixinhaMapper::toResponse).toList();
        }

        redistribuir(valorSobrou, caixinhasArray, totalSomaPontuacao);

        return ordenaCaixinhas(caixinhasArray);
    }

    private void redistribuir(BigDecimal valorSobrou, Caixinha[] caixinhasArray, BigDecimal totalSomaPontuacao) {
        BigDecimal totalSomaPontuacaoTemp = totalSomaPontuacao;
        BigDecimal resto = valorSobrou;
        BigDecimal restoTemp = valorSobrou;
        int ck = 1;
        do {
            for (Caixinha c : caixinhasArray) {
                if (c.isQuitada()) continue;
                BigDecimal investimentoCalculado = c.getPontuacao().divide(totalSomaPontuacao, MathContext.DECIMAL128).multiply(resto);
                BigDecimal diferenca = c.getTotal().subtract(c.getArrecadado()).subtract(c.getInvestimento());
                if (investimentoCalculado.compareTo(diferenca) >= 0) {
                    c.adicionaInvestimento(diferenca);
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
            totalSomaPontuacao = totalSomaPontuacaoTemp;
        } while (resto.compareTo(BigDecimal.valueOf(0.01)) >= 0);

        System.out.println(ck);
    }


    private SomaPontuacaoDTO calculaSomaPontuacao(Caixinha[] caixinhasArray) {
        SomaPontuacaoDTO somaPontuacaoDTO = new SomaPontuacaoDTO();
        for (Caixinha c : caixinhasArray) {
            if (c.isQuitada()) continue;
            c.calculaPontuacao();
            somaPontuacaoDTO.setTotalSomaPontuacao(somaPontuacaoDTO.getTotalSomaPontuacao().add(c.getPontuacao()));
            somaPontuacaoDTO.setSoma(somaPontuacaoDTO.getSoma().add(c.getTotal().subtract(c.getArrecadado())));
        }
        return somaPontuacaoDTO;
    }
}
