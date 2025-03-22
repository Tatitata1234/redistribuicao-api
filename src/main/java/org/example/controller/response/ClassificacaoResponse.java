package org.example.controller.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassificacaoResponse {
    private Long id;
    private String nome;
    private Long valor;
}
