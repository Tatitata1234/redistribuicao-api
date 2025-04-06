package org.example.mapper;

import org.example.controller.request.CaixinhaRequest;
import org.example.controller.response.CaixinhaResponse;
import org.example.model.entity.Caixinha;
import org.example.model.entity.Classificacao;
import org.example.model.entity.Utilidade;

import java.math.RoundingMode;


public class CaixinhaMapper {

    private CaixinhaMapper() {}

    public static CaixinhaResponse toResponse(Caixinha entity) {
        return CaixinhaResponse.builder()
                .id(entity.getId())
                .total(entity.getTotal())
                .nome(entity.getNome())
                .quitada(entity.isQuitada())
                .classificacao(entity.getClassificacao().getNome())
                .utilidade(entity.getUtililidade().getNome())
                .arrecadado(entity.getArrecadado())
                .investimento(entity.getInvestimento().setScale(2, RoundingMode.HALF_EVEN))
                .mensagem(entity.getMensagem())
                .dataVencimento(entity.getDataVencimento())
                .build();
    }

    public static Caixinha toEntity(CaixinhaRequest request) {
        return Caixinha.builder()
                .id(request.getId())
                .arrecadado(request.getArrecadado())
                .classificacao(Classificacao.builder().nome(request.getClassificacao()).build())
                .total(request.getTotal())
                .utililidade(Utilidade.builder().nome(request.getUtilidade()).build())
                .quitada(request.isQuitada())
                .nome(request.getNome())
                .mensagem(request.getMensagem())
                .dataVencimento(request.getDataVencimento())
                .build();
    }

    public static void toQuitada(Caixinha c) {
        c.setMensagem("PODE COMPRAR TUDO!");
        c.setQuitada(true);
        c.setInvestimento(c.getTotal().subtract(c.getArrecadado()));
    }
}
