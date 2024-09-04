package org.example;

public class CaixinhaMapper {
    public static CaixinhaResponse toResponse(Caixinha entity) {
        return CaixinhaResponse.builder()
                .total(entity.getTotal())
                .nome(entity.getNome())
                .quitada(entity.isQuitada())
                .classificacao(entity.getConfigCaixinha().getClassificacao().name())
                .utilidade(entity.getConfigCaixinha().getUtililidade().name())
                .arrecadado(entity.getArrecadado())
                .investimento(entity.getInvestimento())
                .mensagem(entity.getMensagem())
                .build();
    }
}
