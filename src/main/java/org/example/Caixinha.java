package org.example;

import lombok.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Caixinha {
    public static final BigDecimal VALOR_MINIMO = new BigDecimal(5);
    private String nome;
    private BigDecimal total;
    private BigDecimal arrecadado;
    private Classificacao classificacao;
    private Utililidade utililidade;
    private BigDecimal investimento = new BigDecimal(0);
    private BigDecimal pontuacao;
    private BigDecimal porcentagem;
    private boolean quitada;
    private String mensagem;

    public void calculaPontuacao() {
        pontuacao = new BigDecimal(this.classificacao.getValor() *
                this.utililidade.getValor())
                .divide(total, MathContext.DECIMAL128);
    }


    public void calculaPorcentagem(BigDecimal totalSomaPontuacao) {
        this.porcentagem = pontuacao.divide(totalSomaPontuacao, MathContext.DECIMAL128);
    }

    public void adicionaInvestimento(BigDecimal investimentoFinal) {
        if (investimento == null) {
            investimento = investimentoFinal.round(MathContext.DECIMAL32).setScale(2, RoundingMode.HALF_EVEN);
        } else
            this.investimento = this.investimento.add(investimentoFinal).round(MathContext.DECIMAL32).setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getInvestimento() {
        if (investimento == null) {
            this.investimento = new BigDecimal(0);
        }
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
