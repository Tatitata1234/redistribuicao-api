package org.example.controller.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CaixinhaResponse {
    private Long id;
    private String nome;
    private BigDecimal total;
    private BigDecimal arrecadado;
    private String classificacao;
    private String utilidade;
    private BigDecimal investimento = new BigDecimal(0);
    private boolean quitada;
    private String mensagem;
    private LocalDate dataVencimento;
}
