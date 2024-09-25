package org.example.controller;

import jakarta.validation.Valid;
import org.example.controller.request.CaixinhaRequest;
import org.example.controller.response.CaixinhaResponse;
import org.example.service.RedistribuicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/redistribuir")
@CrossOrigin(origins = "https://tatitata1234.github.io")
public class RedistribuicaoController {

    @Autowired
    private RedistribuicaoService service;

    @GetMapping("/hardcode")
    public ResponseEntity<List<CaixinhaResponse>> listar(@RequestParam("investimento") long investimento) {
        if (investimento < 26) {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
        BigDecimal valorSobrou = new BigDecimal(investimento);
        List<CaixinhaResponse> caixinhas = service.calculaDitribuicaoInvestimento(valorSobrou);
        return ResponseEntity.ok(caixinhas);
    }


    @PostMapping("/json")
    public ResponseEntity<List<CaixinhaResponse>> listar(@RequestParam("investimento") long investimento,
                                                         @RequestBody @Valid List<CaixinhaRequest> caixinhas) {
        if (investimento < caixinhas.size()) {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
        BigDecimal valorSobrou = new BigDecimal(investimento);
        List<CaixinhaResponse> caixinhasRedistribuidas = service.calculaDitribuicaoInvestimento(valorSobrou, caixinhas);
        return ResponseEntity.ok(caixinhasRedistribuidas);
    }

    @GetMapping
    public ResponseEntity<List<CaixinhaResponse>> listar(@RequestParam("investimento") long investimento, @RequestHeader("usuario") long usuarioId) {
        if (investimento < 26) {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
        BigDecimal valorSobrou = new BigDecimal(investimento);
        List<CaixinhaResponse> caixinhasRedistribuidas = service.calculaDitribuicaoInvestimento(valorSobrou, usuarioId);
        return ResponseEntity.ok(caixinhasRedistribuidas);
    }
}