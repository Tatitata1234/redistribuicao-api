package org.example.mapper;

import org.example.controller.response.ClassificacaoResponse;
import org.example.model.entity.Classificacao;

public class ClassificacaoMapper {

    private ClassificacaoMapper(){}

    public static ClassificacaoResponse toResponse(Classificacao classificacao) {
        return ClassificacaoResponse.builder()
                .id(classificacao.getId())
                .nome(classificacao.getNome())
                .valor(classificacao.getValor())
                .build();
    }
}
