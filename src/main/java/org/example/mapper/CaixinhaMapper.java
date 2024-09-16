package org.example.mapper;

import org.example.controller.request.CaixinhaRequest;
import org.example.controller.response.CaixinhaResponse;
import org.example.model.Caixinha;
import org.example.model.CaixinhaDouble;
import org.example.model.enumerator.Classificacao;
import org.example.model.enumerator.Utililidade;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class CaixinhaMapper {

    private CaixinhaMapper() {

    }

    public static CaixinhaResponse toResponse(Caixinha entity) {
        return CaixinhaResponse.builder()
                .total(entity.getTotal())
                .nome(entity.getNome())
                .quitada(entity.isQuitada())
                .classificacao(entity.getClassificacao().name())
                .utilidade(entity.getUtililidade().name())
                .arrecadado(entity.getArrecadado())
                .investimento(entity.getInvestimento())
                .mensagem(entity.getMensagem())
                .build();
    }

    public static CaixinhaResponse toResponse(CaixinhaDouble entity) {
        return CaixinhaResponse.builder()
                .total(BigDecimal.valueOf(entity.getTotal()))
                .nome(entity.getNome())
                .quitada(entity.isQuitada())
                .classificacao(entity.getClassificacao().name())
                .utilidade(entity.getUtililidade().name())
                .arrecadado(BigDecimal.valueOf(entity.getArrecadado()).round(MathContext.DECIMAL32).setScale(2, RoundingMode.HALF_EVEN))
                .investimento(BigDecimal.valueOf(entity.getInvestimento()).round(MathContext.DECIMAL32).setScale(2, RoundingMode.HALF_EVEN))
                .mensagem(entity.getMensagem())
                .build();
    }

    public static Caixinha toEntity(CaixinhaRequest request) {
        return Caixinha.builder()
                .arrecadado(request.getArrecadado())
                .classificacao(Classificacao.valueOf(request.getClassificacao()))
                .total(request.getTotal())
                .utililidade(Utililidade.valueOf(request.getUtilidade()))
                .quitada(request.isQuitada())
                .nome(request.getNome())
                .mensagem(request.getMensagem())
                .build();
    }
}
