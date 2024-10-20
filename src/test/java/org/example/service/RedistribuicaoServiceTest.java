package org.example.service;

import org.example.controller.response.CaixinhaResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

public class RedistribuicaoServiceTest {
    @Autowired
    private RedistribuicaoService redistribuicaoService;

    @Test
    public void deveSomarValorTotalRecebido(){
        //List<CaixinhaResponse> lista = redistribuicaoService.calculaDitribuicaoInvestimento(BigDecimal.valueOf(2000),1l);

    }
}
