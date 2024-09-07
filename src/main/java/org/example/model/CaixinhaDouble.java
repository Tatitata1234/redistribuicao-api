package org.example.model;

import lombok.*;
import org.example.model.enumerator.Classificacao;
import org.example.model.enumerator.Utililidade;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CaixinhaDouble {
    public static final double VALOR_MINIMO = 5;
    private String nome;
    private double total;
    private double arrecadado;
    private Classificacao classificacao;
    private Utililidade utililidade;
    private double investimento = 0;
    private double pontuacao;
    private boolean quitada;
    private String mensagem;

    public void calculaPontuacao() {
        pontuacao = this.classificacao.getValor() *
                this.utililidade.getValor() / total;
    }

    public void adicionaInvestimento(double investimentoFinal) {
        this.investimento = this.investimento + investimentoFinal;
    }

    public double getInvestimento() {
        return investimento;
    }

    public void setMensagem(String mensagem) {
        if (Objects.isNull(this.mensagem)) {
            this.mensagem = mensagem;
        } else {
            this.mensagem += "\n" + mensagem;
        }
    }

    public String getMensagem() {
        if (Objects.isNull(this.mensagem)) {
            this.mensagem = "";
        }
        return this.mensagem;
    }
}
