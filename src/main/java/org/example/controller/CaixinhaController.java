package org.example.controller;

import jakarta.validation.Valid;
import org.example.controller.request.CaixinhaRequest;
import org.example.controller.response.CaixinhaResponse;
import org.example.service.CaixinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/caixinhas")
@CrossOrigin(origins = "https://tatitata1234.github.io/redistribuicao/")
public class CaixinhaController {

    @Autowired
    private CaixinhaService caixinhaService;

    @GetMapping("/configDefault")
    public ResponseEntity<List<CaixinhaResponse>> listar(@RequestParam("investimento") long investimento) {
        if (investimento < 26) {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
        BigDecimal valorSobrou = new BigDecimal(investimento);
        List<CaixinhaResponse> caixinhas = caixinhaService.calculaDitribuicaoInvestimento(valorSobrou);
        return ResponseEntity.ok(caixinhas);
    }


    @GetMapping
    public ResponseEntity<List<CaixinhaResponse>> listar(@RequestParam("investimento") long investimento, @RequestBody @Valid List<CaixinhaRequest> caixinhas) {
        if (investimento < 26) {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
        BigDecimal valorSobrou = new BigDecimal(investimento);
        List<CaixinhaResponse> caixinhasRedistribuidas = caixinhaService.calculaDitribuicaoInvestimento(valorSobrou, caixinhas);
        return ResponseEntity.ok(caixinhasRedistribuidas);
    }
}