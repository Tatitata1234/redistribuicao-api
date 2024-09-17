package org.example.model.entity;

import lombok.*;
import org.example.model.enumerator.Classificacao;
import org.example.model.enumerator.Utililidade;

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
        this.pontuacao = new BigDecimal(this.classificacao.getValor() *
                this.utililidade.getValor())
                .divide(total.subtract(arrecadado), MathContext.DECIMAL128);
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

    public void setMensagem() {
        if (this.quitada) {
            this.mensagem = ("Pode comprar!");
        }
        if (this.investimento.compareTo(VALOR_MINIMO) < 0) {
            this.mensagem = ("Menor que valor programado!");
        }
    }

    public String getMensagem() {
        if (Objects.isNull(this.mensagem)) {
            this.mensagem = "";
        }
        return this.mensagem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Caixinha caixinha = (Caixinha) o;
        return quitada == caixinha.quitada && nome.equals(caixinha.nome) && total.equals(caixinha.total) && arrecadado.equals(caixinha.arrecadado) && classificacao == caixinha.classificacao && utililidade == caixinha.utililidade && Objects.equals(investimento, caixinha.investimento) && Objects.equals(pontuacao, caixinha.pontuacao) && Objects.equals(porcentagem, caixinha.porcentagem) && Objects.equals(mensagem, caixinha.mensagem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, total, arrecadado, classificacao, utililidade, investimento, pontuacao, porcentagem, quitada, mensagem);
    }
}
