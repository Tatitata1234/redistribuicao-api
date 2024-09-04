package org.example;


public class ConfigCaixinhaMapper {
    public static ConfigCaixinhaResponse toResponse(ConfigCaixinha entity) {
        return ConfigCaixinhaResponse.builder()
                .utililidade(entity.getUtililidade().name())
                .classificacao(entity.getClassificacao().name())
                .build();
    }
}
