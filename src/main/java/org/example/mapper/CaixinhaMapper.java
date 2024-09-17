package org.example.mapper;

import org.example.controller.request.CaixinhaRequest;
import org.example.controller.response.CaixinhaResponse;
import org.example.model.entity.Caixinha;
import org.example.model.enumerator.Classificacao;
import org.example.model.enumerator.Utililidade;


public class CaixinhaMapper {

    private CaixinhaMapper() {}

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

    public static void toQuitada(Caixinha c) {
        c.setMensagem("PODE COMPRAR TUDO!");
        c.setQuitada(true);
        c.setInvestimento(c.getTotal().subtract(c.getArrecadado()));
    }
}
