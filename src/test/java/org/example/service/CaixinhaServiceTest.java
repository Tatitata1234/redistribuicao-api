package org.example.service;

import org.example.controller.request.CaixinhaRequest;
import org.example.exception.CaixinhaNaoEncontradaException;
import org.example.model.entity.Caixinha;
import org.example.repository.CaixinhaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class CaixinhaServiceTest {

    @Mock
    private CaixinhaRepository caixinhaRepository;

    @InjectMocks
    private CaixinhaService caixinhaService;

    private CaixinhaRequest buildRequest() {
        return CaixinhaRequest.builder()
                .id(1L)
                .nome("Viagem")
                .mensagem("Quero viajar")
                .arrecadado(BigDecimal.valueOf(500))
                .total(BigDecimal.valueOf(2000))
                .quitada(false)
                .dataVencimento(LocalDate.of(2025, 5, 10))
                .build();
    }

    private Caixinha buildCaixinhaFromRequest(CaixinhaRequest request) {
        Caixinha caixinha = new Caixinha();
        caixinha.setId(request.getId());
        caixinha.setNome("Antigo");
        caixinha.setMensagem("Mensagem antiga");
        caixinha.setArrecadado(BigDecimal.valueOf(100));
        caixinha.setTotal(BigDecimal.valueOf(1000));
        caixinha.setQuitada(true);
        caixinha.setDataVencimento(LocalDate.of(2025, 1, 1));
        return caixinha;
    }

    @Test
    public void deveEditarCaixinhaComSucesso() {
        CaixinhaRequest request = buildRequest();
        Caixinha existente = buildCaixinhaFromRequest(request);

        when(caixinhaRepository.findById(1L)).thenReturn(Optional.of(existente));

        caixinhaService.edita(request);

        assertEquals("Viagem", existente.getNome());
        assertEquals(BigDecimal.valueOf(500), existente.getArrecadado());
        assertEquals(BigDecimal.valueOf(2000), existente.getTotal());
        assertFalse(existente.isQuitada());
        assertEquals(LocalDate.of(2025, 5, 10), existente.getDataVencimento());

        verify(caixinhaRepository).save(existente);
    }

    @Test(expected = CaixinhaNaoEncontradaException.class)
    public void deveLancarExcecaoQuandoCaixinhaNaoForEncontrada() {
        CaixinhaRequest request = buildRequest();

        when(caixinhaRepository.findById(1L)).thenReturn(Optional.empty());

        caixinhaService.edita(request);
    }
}
