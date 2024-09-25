package org.example.auth.mapper;

import org.example.auth.controller.response.UsuarioResponse;
import org.example.model.entity.Usuario;

public class UsuarioMapper {

    private UsuarioMapper() {}

    public static UsuarioResponse toResponse(Usuario entity) {
        return UsuarioResponse.builder().nomeUsuario(entity.getNomeUsuario())
                .id(entity.getId())
                .build();
    }
}
