package org.example.service.utils;

import org.example.controller.request.CaixinhaRequest;
import org.example.controller.response.CaixinhaResponse;
import org.example.model.entity.Caixinha;
import org.example.model.entity.Classificacao;
import org.example.model.entity.Usuario;
import org.example.model.entity.Utilidade;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class CaixinhaUtilsTest {

    @Test
    public void deveOrdenarCaixinhasAposCalculo() {
        Caixinha c1 = Caixinha.builder()
                .id(1L)
                .nome("Caixinha 1")
                .classificacao(Classificacao.builder().build())
                .utililidade(Utilidade.builder().build())
                .usuario(Usuario.builder().id(1L).build())
                .investimento(BigDecimal.valueOf(10))
                .build();
        Caixinha c2 = Caixinha.builder()
                .id(2L)
                .ativo(true)
                .quitada(false)
                .arrecadado(BigDecimal.valueOf(10))
                .total(BigDecimal.valueOf(1000))
                .nome("Caixinha 2")
                .classificacao(Classificacao.builder().build())
                .utililidade(Utilidade.builder().build())
                .usuario(Usuario.builder().id(1L).build())
                .investimento(BigDecimal.valueOf(11))
                .build();
        Caixinha c3 = Caixinha.builder()
                .id(3L)
                .ativo(true)
                .quitada(false)
                .arrecadado(BigDecimal.valueOf(10))
                .total(BigDecimal.valueOf(1000))
                .nome("Caixinha 3")
                .classificacao(Classificacao.builder().build())
                .utililidade(Utilidade.builder().build())
                .usuario(Usuario.builder().id(1L).build())
                .investimento(BigDecimal.valueOf(12))
                .build();

        List<Caixinha> input = Arrays.asList(c1, c2, c3);
        List<CaixinhaResponse> resultado = CaixinhaUtils.ordenaCaixinhas(input);

        assertEquals(3, resultado.size());
        assertEquals("Caixinha 3", resultado.get(0).getNome());
        assertEquals("Caixinha 2", resultado.get(1).getNome());
        assertEquals("Caixinha 1", resultado.get(2).getNome());
    }

    @Test
    public void deveOrdenarCaixinhasAposCalculoComChavesIguais() {
        Caixinha c1 = Caixinha.builder()
                .id(1L)
                .nome("Caixinha 1")
                .classificacao(Classificacao.builder().build())
                .utililidade(Utilidade.builder().build())
                .usuario(Usuario.builder().id(1L).build())
                .investimento(BigDecimal.valueOf(100))
                .build();
        Caixinha c2 = Caixinha.builder()
                .id(1L)
                .ativo(true)
                .quitada(false)
                .arrecadado(BigDecimal.valueOf(10))
                .total(BigDecimal.valueOf(1000))
                .nome("Caixinha 2")
                .classificacao(Classificacao.builder().build())
                .utililidade(Utilidade.builder().build())
                .usuario(Usuario.builder().id(1L).build())
                .investimento(BigDecimal.valueOf(100))
                .build();

        List<Caixinha> input = Arrays.asList(c1, c2);
        List<CaixinhaResponse> resultado = CaixinhaUtils.ordenaCaixinhas(input);

        assertEquals(2, resultado.size());
        assertEquals("Caixinha 1", resultado.get(0).getNome());
        assertEquals("Caixinha 2", resultado.get(1).getNome());
    }

    @Test
    public void deveOrdenarPreRedistribuicaoComChavesDiferentes() {
        Caixinha c1 = Caixinha.builder()
                .id(1L)
                .ativo(true)
                .quitada(false)
                .arrecadado(BigDecimal.valueOf(10))
                .total(BigDecimal.valueOf(1000))
                .nome("Caixinha 1")
                .classificacao(Classificacao.builder().build())
                .utililidade(Utilidade.builder().build())
                .usuario(Usuario.builder().id(1L).build())
                .dataVencimento(LocalDate.now().plus(3, ChronoUnit.MONTHS))
                .build();
        Caixinha c2 = Caixinha.builder()
                .id(2L)
                .ativo(true)
                .quitada(false)
                .arrecadado(BigDecimal.valueOf(10))
                .total(BigDecimal.valueOf(10000))
                .nome("Caixinha 2")
                .classificacao(Classificacao.builder().build())
                .utililidade(Utilidade.builder().build())
                .usuario(Usuario.builder().id(1L).build())
                .dataVencimento(LocalDate.now().plus(3, ChronoUnit.MONTHS))
                .build();
        Caixinha c3 = Caixinha.builder()
                .id(3L)
                .ativo(true)
                .quitada(false)
                .arrecadado(BigDecimal.valueOf(10))
                .total(BigDecimal.valueOf(1000))
                .nome("Caixinha 3")
                .classificacao(Classificacao.builder().build())
                .utililidade(Utilidade.builder().build())
                .usuario(Usuario.builder().id(1L).build())
                .dataVencimento(LocalDate.now().plus(4, ChronoUnit.MONTHS))
                .build();
        Caixinha c4 = Caixinha.builder()
                .id(4L)
                .ativo(true)
                .quitada(false)
                .arrecadado(BigDecimal.valueOf(10))
                .total(BigDecimal.valueOf(1000))
                .nome("Caixinha 4")
                .classificacao(Classificacao.builder().build())
                .utililidade(Utilidade.builder().build())
                .usuario(Usuario.builder().id(1L).build())
                .dataVencimento(null)
                .build();
        Caixinha c5 = Caixinha.builder()
                .id(5L)
                .ativo(true)
                .quitada(false)
                .arrecadado(BigDecimal.valueOf(10))
                .total(BigDecimal.valueOf(10000))
                .nome("Caixinha 5")
                .classificacao(Classificacao.builder().build())
                .utililidade(Utilidade.builder().build())
                .usuario(Usuario.builder().id(1L).build())
                .dataVencimento(null)
                .build();
        Caixinha c6 = Caixinha.builder()
                .id(6L)
                .ativo(true)
                .quitada(false)
                .arrecadado(BigDecimal.valueOf(10))
                .total(BigDecimal.valueOf(10000))
                .nome("Caixinha 6")
                .classificacao(Classificacao.builder().build())
                .utililidade(Utilidade.builder().build())
                .usuario(Usuario.builder().id(1L).build())
                .dataVencimento(null)
                .build();

        List<Caixinha> input = Arrays.asList(c1, c2, c3, c4, c5, c6);
        List<Caixinha> resultado = CaixinhaUtils.ordenaPreRedistribuicao(input);

        assertEquals(6, resultado.size());
        assertEquals("Caixinha 1", resultado.get(0).getNome());
        assertEquals("Caixinha 2", resultado.get(1).getNome());
        assertEquals("Caixinha 3", resultado.get(2).getNome());
        assertEquals("Caixinha 4", resultado.get(3).getNome());
        assertEquals("Caixinha 5", resultado.get(4).getNome());
        assertEquals("Caixinha 6", resultado.get(5).getNome());
    }

    @Test
    public void deveOrdenaPreRedistribuicaoComChavesIguais() {
        Caixinha c1 = Caixinha.builder()
                .id(1L)
                .ativo(true)
                .quitada(false)
                .arrecadado(BigDecimal.valueOf(10))
                .total(BigDecimal.valueOf(1000))
                .nome("Caixinha 1")
                .classificacao(Classificacao.builder().build())
                .utililidade(Utilidade.builder().build())
                .usuario(Usuario.builder().id(1L).build())
                .dataVencimento(LocalDate.now().plus(3, ChronoUnit.MONTHS))
                .build();
        Caixinha c2 = Caixinha.builder()
                .id(1L)
                .ativo(true)
                .quitada(false)
                .arrecadado(BigDecimal.valueOf(10))
                .total(BigDecimal.valueOf(1000))
                .nome("Caixinha 2")
                .classificacao(Classificacao.builder().build())
                .utililidade(Utilidade.builder().build())
                .usuario(Usuario.builder().id(1L).build())
                .dataVencimento(LocalDate.now().plus(3, ChronoUnit.MONTHS))
                .build();

        List<Caixinha> input = Arrays.asList(c1, c2);
        List<Caixinha> resultado = CaixinhaUtils.ordenaPreRedistribuicao(input);

        assertEquals(2, resultado.size());
        assertEquals("Caixinha 1", resultado.get(0).getNome());
        assertEquals("Caixinha 2", resultado.get(1).getNome());
    }
}
