package org.example;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CaixinhaResponse {
    private String nome;
    private BigDecimal total;
    private BigDecimal arrecadado;
    private String classificacao;
    private String utilidade;
    private BigDecimal investimento = new BigDecimal(0);
    private boolean quitada;
    private String mensagem;
}
