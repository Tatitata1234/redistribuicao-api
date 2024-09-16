package org.example.controller.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CaixinhaRequest {
    private String nome;
    private BigDecimal total;
    private BigDecimal arrecadado;
    private String classificacao;
    private String utilidade;
    private boolean quitada;
    private String mensagem;
}
