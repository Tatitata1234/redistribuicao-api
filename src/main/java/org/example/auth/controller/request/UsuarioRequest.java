package org.example.auth.controller.request;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UsuarioRequest {
    private String nomeUsuario;
    private String senha;
}
