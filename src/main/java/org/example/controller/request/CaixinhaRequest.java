package org.example.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CaixinhaRequest {

    @NotEmpty(message = "Campo 'nome' obrigatório")
    private String nome;
    @NotNull
    private BigDecimal total;
    @NotNull
    private BigDecimal arrecadado;
    @NotEmpty
    private String classificacao;
    @NotEmpty
    private String utilidade;
    @NotNull
    private boolean quitada;
    private String mensagem;
}
