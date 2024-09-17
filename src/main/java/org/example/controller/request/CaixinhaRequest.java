package org.example.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaixinhaRequest that = (CaixinhaRequest) o;
        return quitada == that.quitada && nome.equals(that.nome) && total.equals(that.total) && arrecadado.equals(that.arrecadado) && classificacao.equals(that.classificacao) && utilidade.equals(that.utilidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, total, arrecadado, classificacao, utilidade, quitada, mensagem);
    }
}
