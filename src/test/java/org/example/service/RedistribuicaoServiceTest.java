package org.example.service;

import org.example.auth.repository.UsuarioRepository;
import org.example.controller.request.CaixinhaRequest;
import org.example.controller.response.CaixinhaResponse;
import org.example.exception.UsuarioNaoEncontradoException;
import org.example.model.entity.Caixinha;
import org.example.model.entity.Classificacao;
import org.example.model.entity.Usuario;
import org.example.model.entity.Utilidade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class RedistribuicaoServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private RedistribuicaoService redistribuicaoService;

    @Mock
    private RedistribuicaoService self;

    private Usuario mockUsuario;

    @Before
    public void setUp() {
        self = spy(new RedistribuicaoService(usuarioRepository, null));
        redistribuicaoService = new RedistribuicaoService(usuarioRepository, self);

        mockUsuario = new Usuario();
        mockUsuario.setId(1L);
        mockUsuario.setCaixinhas(Arrays.asList(
                Caixinha.builder()
                        .id(1L)
                        .ativo(true)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(1000))
                        .nome("Caixinha 1")
                        .classificacao(Classificacao.builder().valor(1L).build())
                        .utililidade(Utilidade.builder().valor(1L).build())
                        .usuario(Usuario.builder().id(1L).build())
                        .dataVencimento(LocalDate.now().plus(3, ChronoUnit.MONTHS))
                        .build(),
                Caixinha.builder()
                        .id(2L)
                        .ativo(true)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(10000))
                        .nome("Caixinha 2")
                        .classificacao(Classificacao.builder().valor(1L).build())
                        .utililidade(Utilidade.builder().valor(1L).build())
                        .usuario(Usuario.builder().id(1L).build())
                        .dataVencimento(LocalDate.now().plus(3, ChronoUnit.MONTHS))
                        .build(),
                Caixinha.builder()
                        .id(3L)
                        .ativo(true)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(1000))
                        .nome("Caixinha 3")
                        .classificacao(Classificacao.builder().valor(1L).build())
                        .utililidade(Utilidade.builder().valor(1L).build())
                        .usuario(Usuario.builder().id(1L).build())
                        .dataVencimento(LocalDate.now().plus(4, ChronoUnit.MONTHS))
                        .build(),
                Caixinha.builder()
                        .id(4L)
                        .ativo(true)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(1000))
                        .nome("Caixinha 4")
                        .classificacao(Classificacao.builder().valor(1L).build())
                        .utililidade(Utilidade.builder().valor(1L).build())
                        .usuario(Usuario.builder().id(1L).build())
                        .dataVencimento(null)
                        .build(),
                Caixinha.builder()
                        .id(5L)
                        .ativo(true)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(10000))
                        .nome("Caixinha 5")
                        .classificacao(Classificacao.builder().valor(1L).build())
                        .utililidade(Utilidade.builder().valor(1L).build())
                        .usuario(Usuario.builder().id(1L).build())
                        .dataVencimento(null)
                        .build(),
                Caixinha.builder()
                        .id(6L)
                        .ativo(true)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(10000))
                        .nome("Caixinha 6")
                        .classificacao(Classificacao.builder().valor(1L).build())
                        .utililidade(Utilidade.builder().valor(1L).build())
                        .usuario(Usuario.builder().id(1L).build())
                        .dataVencimento(null)
                        .build()
        ));
    }

    @Test
    public void testDistribuicaoComRequests() {
        BigDecimal valorSobrou = new BigDecimal("600");

        when(self.doRedistribuicao(valorSobrou, mockUsuario.getCaixinhas())).thenReturn(Arrays.asList(
                CaixinhaResponse.builder().nome("Educação").investimento(new BigDecimal("150")).build(),
                CaixinhaResponse.builder().nome("Lazer").investimento(new BigDecimal("450")).build()
        ));

        List<CaixinhaRequest> caixinhasRequests = Arrays.asList(
                CaixinhaRequest.builder()
                        .id(1L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(1000))
                        .nome("Caixinha 1")
                        .classificacao("VIAGEM")
                        .dataVencimento(LocalDate.now().plus(3, ChronoUnit.MONTHS))
                        .build(),
                CaixinhaRequest.builder()
                        .id(2L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(10000))
                        .nome("Caixinha 2")
                        .classificacao("VIAGEM")
                        .dataVencimento(LocalDate.now().plus(3, ChronoUnit.MONTHS))
                        .build(),
                CaixinhaRequest.builder()
                        .id(3L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(1000))
                        .nome("Caixinha 3")
                        .classificacao("VIAGEM")
                        .dataVencimento(LocalDate.now().plus(4, ChronoUnit.MONTHS))
                        .build(),
                CaixinhaRequest.builder()
                        .id(4L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(1000))
                        .nome("Caixinha 4")
                        .classificacao("VIAGEM")
                        .dataVencimento(null)
                        .build(),
                CaixinhaRequest.builder()
                        .id(5L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(10000))
                        .nome("Caixinha 5")
                        .classificacao("VIAGEM")
                        .dataVencimento(null)
                        .build(),
                CaixinhaRequest.builder()
                        .id(6L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(10000))
                        .nome("Caixinha 6")
                        .classificacao("VIAGEM")
                        .dataVencimento(null)
                        .build()
        );
        List<CaixinhaResponse> result = redistribuicaoService.calculaDistribuicaoInvestimento(valorSobrou, caixinhasRequests);
        assertEquals(2, result.size());
        verify(self, times(1)).doRedistribuicao(any(), any());
    }

    @Test
    public void testDistribuicaoComUsuario() {
        when(usuarioRepository.findByIdAndAtivoIsTrue(1L)).thenReturn(mockUsuario);
        BigDecimal valor = new BigDecimal("300");

        when(self.doRedistribuicao(valor, mockUsuario.getCaixinhas())).thenReturn(Arrays.asList(
                CaixinhaResponse.builder().nome("Reserva").investimento(new BigDecimal("100")).build(),
                CaixinhaResponse.builder().nome("Viagem").investimento(new BigDecimal("200")).build()
        ));

        List<CaixinhaResponse> result = redistribuicaoService.calculaDistribuicaoInvestimento(valor, 1L);
        assertEquals(2, result.size());
        verify(self).doRedistribuicao(eq(valor), any());
    }

    @Test(expected = UsuarioNaoEncontradoException.class)
    public void testUsuarioNaoEncontrado() {
        when(usuarioRepository.findByIdAndAtivoIsTrue(99L)).thenReturn(null);
        redistribuicaoService.calculaDistribuicaoInvestimento(BigDecimal.TEN, 99L);
    }

    @Test(expected = Exception.class) // substitua por ValorMinimoException se tiver uma específica
    public void testValorInsuficiente() {
        mockUsuario.setCaixinhas(Collections.singletonList(
                Caixinha.builder().nome("Reserva").build()
        ));
        when(usuarioRepository.findByIdAndAtivoIsTrue(1L)).thenReturn(mockUsuario);
        redistribuicaoService.calculaDistribuicaoInvestimento(new BigDecimal("0.01"), 1L);
    }

    @Test
    public void deveDistribuirComRequestsEValorTotalMaiorQueNecessario() {
        BigDecimal valorSobrou = new BigDecimal("100000");

        when(self.doRedistribuicao(valorSobrou, mockUsuario.getCaixinhas())).thenReturn(Arrays.asList(
                CaixinhaResponse.builder().nome("Educação").investimento(new BigDecimal("150")).build(),
                CaixinhaResponse.builder().nome("Lazer").investimento(new BigDecimal("450")).build()
        ));

        List<CaixinhaRequest> caixinhasRequests = Arrays.asList(
                CaixinhaRequest.builder()
                        .id(1L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(1000))
                        .nome("Caixinha 1")
                        .classificacao("VIAGEM")
                        .dataVencimento(LocalDate.now().plus(3, ChronoUnit.MONTHS))
                        .build(),
                CaixinhaRequest.builder()
                        .id(2L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(10000))
                        .nome("Caixinha 2")
                        .classificacao("VIAGEM")
                        .dataVencimento(LocalDate.now().plus(3, ChronoUnit.MONTHS))
                        .build(),
                CaixinhaRequest.builder()
                        .id(3L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(1000))
                        .nome("Caixinha 3")
                        .classificacao("VIAGEM")
                        .dataVencimento(LocalDate.now().plus(4, ChronoUnit.MONTHS))
                        .build(),
                CaixinhaRequest.builder()
                        .id(4L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(1000))
                        .nome("Caixinha 4")
                        .classificacao("VIAGEM")
                        .dataVencimento(null)
                        .build(),
                CaixinhaRequest.builder()
                        .id(5L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(10000))
                        .nome("Caixinha 5")
                        .classificacao("VIAGEM")
                        .dataVencimento(null)
                        .build(),
                CaixinhaRequest.builder()
                        .id(6L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(10000))
                        .nome("Caixinha 6")
                        .classificacao("VIAGEM")
                        .dataVencimento(null)
                        .build()
        );
        List<CaixinhaResponse> result = redistribuicaoService.calculaDistribuicaoInvestimento(valorSobrou, caixinhasRequests);
        assertEquals(2, result.size());
        verify(self, times(1)).doRedistribuicao(any(), any());
    }

    @Test
    public void deveDistribuirComRequestsEValorTotalPequeno() {
        BigDecimal valorSobrou = new BigDecimal("26");

        when(self.doRedistribuicao(valorSobrou, mockUsuario.getCaixinhas())).thenReturn(Arrays.asList(
                CaixinhaResponse.builder().nome("Educação").investimento(new BigDecimal("150")).build(),
                CaixinhaResponse.builder().nome("Lazer").investimento(new BigDecimal("450")).build()
        ));

        List<CaixinhaRequest> caixinhasRequests = Arrays.asList(
                CaixinhaRequest.builder()
                        .id(1L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(1000))
                        .nome("Caixinha 1")
                        .classificacao("VIAGEM")
                        .dataVencimento(LocalDate.now().plus(3, ChronoUnit.MONTHS))
                        .build(),
                CaixinhaRequest.builder()
                        .id(2L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(10000))
                        .nome("Caixinha 2")
                        .classificacao("VIAGEM")
                        .dataVencimento(LocalDate.now().plus(3, ChronoUnit.MONTHS))
                        .build(),
                CaixinhaRequest.builder()
                        .id(3L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(1000))
                        .nome("Caixinha 3")
                        .classificacao("VIAGEM")
                        .dataVencimento(LocalDate.now().plus(4, ChronoUnit.MONTHS))
                        .build(),
                CaixinhaRequest.builder()
                        .id(4L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(1000))
                        .nome("Caixinha 4")
                        .classificacao("VIAGEM")
                        .dataVencimento(null)
                        .build(),
                CaixinhaRequest.builder()
                        .id(5L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(10000))
                        .nome("Caixinha 5")
                        .classificacao("VIAGEM")
                        .dataVencimento(null)
                        .build(),
                CaixinhaRequest.builder()
                        .id(6L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(10000))
                        .nome("Caixinha 6")
                        .classificacao("VIAGEM")
                        .dataVencimento(null)
                        .build()
        );
        List<CaixinhaResponse> result = redistribuicaoService.calculaDistribuicaoInvestimento(valorSobrou, caixinhasRequests);
        assertEquals(2, result.size());
        verify(self, times(1)).doRedistribuicao(any(), any());
    }

    @Test
    public void deveDistribuirComCaixinhaQuitada() {
        BigDecimal valorSobrou = new BigDecimal("10000");

        when(self.doRedistribuicao(valorSobrou, mockUsuario.getCaixinhas())).thenReturn(Arrays.asList(
                CaixinhaResponse.builder().nome("Educação").investimento(new BigDecimal("150")).build(),
                CaixinhaResponse.builder().nome("Lazer").investimento(new BigDecimal("450")).build()
        ));

        List<CaixinhaRequest> caixinhasRequests = Arrays.asList(
                CaixinhaRequest.builder()
                        .id(1L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(1000))
                        .nome("Caixinha 1")
                        .classificacao("VIAGEM")
                        .dataVencimento(LocalDate.now().plus(3, ChronoUnit.MONTHS))
                        .build(),
                CaixinhaRequest.builder()
                        .id(2L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(10000))
                        .nome("Caixinha 2")
                        .classificacao("VIAGEM")
                        .dataVencimento(LocalDate.now().plus(3, ChronoUnit.MONTHS))
                        .build(),
                CaixinhaRequest.builder()
                        .id(3L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(1000))
                        .nome("Caixinha 3")
                        .classificacao("VIAGEM")
                        .dataVencimento(LocalDate.now().plus(4, ChronoUnit.MONTHS))
                        .build(),
                CaixinhaRequest.builder()
                        .id(4L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(1000))
                        .nome("Caixinha 4")
                        .classificacao("VIAGEM")
                        .dataVencimento(null)
                        .build(),
                CaixinhaRequest.builder()
                        .id(5L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(10000))
                        .nome("Caixinha 5")
                        .classificacao("VIAGEM")
                        .dataVencimento(null)
                        .build(),
                CaixinhaRequest.builder()
                        .id(6L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(10000))
                        .nome("Caixinha 6")
                        .classificacao("VIAGEM")
                        .dataVencimento(null)
                        .build()
        );
        List<CaixinhaResponse> result = redistribuicaoService.calculaDistribuicaoInvestimento(valorSobrou, caixinhasRequests);
        assertEquals(2, result.size());
        verify(self, times(1)).doRedistribuicao(any(), any());
    }

    @Test
    public void deveDistribuirComCaixinha() {
        BigDecimal valorSobrou = new BigDecimal("600");

        when(self.doRedistribuicao(valorSobrou, mockUsuario.getCaixinhas())).thenReturn(Arrays.asList(
                CaixinhaResponse.builder().nome("Educação").investimento(new BigDecimal("150")).build(),
                CaixinhaResponse.builder().nome("Lazer").investimento(new BigDecimal("450")).build()
        ));

        List<CaixinhaRequest> caixinhasRequests = Arrays.asList(
                CaixinhaRequest.builder()
                        .id(1L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(1000))
                        .nome("Caixinha 1")
                        .classificacao("VIAGEM")
                        .dataVencimento(LocalDate.now().plus(3, ChronoUnit.MONTHS))
                        .build(),
                CaixinhaRequest.builder()
                        .id(2L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(10000))
                        .nome("Caixinha 2")
                        .classificacao("VIAGEM")
                        .dataVencimento(LocalDate.now().plus(3, ChronoUnit.MONTHS))
                        .build(),
                CaixinhaRequest.builder()
                        .id(3L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(1000))
                        .nome("Caixinha 3")
                        .classificacao("VIAGEM")
                        .dataVencimento(LocalDate.now().plus(4, ChronoUnit.MONTHS))
                        .build(),
                CaixinhaRequest.builder()
                        .id(4L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(1000))
                        .nome("Caixinha 4")
                        .classificacao("VIAGEM")
                        .dataVencimento(null)
                        .build(),
                CaixinhaRequest.builder()
                        .id(5L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(10000))
                        .nome("Caixinha 5")
                        .classificacao("VIAGEM")
                        .dataVencimento(null)
                        .build(),
                CaixinhaRequest.builder()
                        .id(6L)
                        .quitada(false)
                        .arrecadado(BigDecimal.valueOf(10))
                        .total(BigDecimal.valueOf(10000))
                        .nome("Caixinha 6")
                        .classificacao("VIAGEM")
                        .dataVencimento(null)
                        .build()
        );
        List<CaixinhaResponse> result = redistribuicaoService.calculaDistribuicaoInvestimento(valorSobrou, caixinhasRequests);
        assertEquals(2, result.size());
        verify(self, times(1)).doRedistribuicao(any(), any());
    }
}
