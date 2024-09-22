package org.example.mapper;

import org.example.controller.request.CaixinhaRequest;
import org.example.controller.response.CaixinhaResponse;
import org.example.model.entity.Caixinha;
import org.example.model.entity.Classificacao;
import org.example.model.entity.Utilidade;


public class CaixinhaMapper {

    private CaixinhaMapper() {}

    public static CaixinhaResponse toResponse(Caixinha entity) {
        return CaixinhaResponse.builder()
                .total(entity.getTotal())
                .nome(entity.getNome())
                .quitada(entity.isQuitada())
                .classificacao(entity.getClassificacao().getNome())
                .utilidade(entity.getUtililidade().getNome())
                .arrecadado(entity.getArrecadado())
                .investimento(entity.getInvestimento())
                .mensagem(entity.getMensagem())
                .build();
    }

    public static Caixinha toEntity(CaixinhaRequest request) {
        return Caixinha.builder()
                .arrecadado(request.getArrecadado())
                .classificacao(Classificacao.builder().nome(request.getClassificacao()).build())
                .total(request.getTotal())
                .utililidade(Utilidade.builder().nome(request.getUtilidade()).build())
                .quitada(request.isQuitada())
                .nome(request.getNome())
                .mensagem(request.getMensagem())
                .build();
    }

    public static void toQuitada(Caixinha c) {
        c.setMensagem("PODE COMPRAR TUDO!");
        c.setQuitada(true);
        c.setInvestimento(c.getTotal().subtract(c.getArrecadado()));
    }
}
