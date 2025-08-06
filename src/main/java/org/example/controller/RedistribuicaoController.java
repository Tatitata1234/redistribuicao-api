package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controller.request.CaixinhaRequest;
import org.example.controller.response.CaixinhaResponse;
import org.example.service.RedistribuicaoService;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/redistribuir")
@CrossOrigin(origins = "https://tatitata1234.github.io")
@RequiredArgsConstructor
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RedistribuicaoController {

    private final RedistribuicaoService service;

    private final Logger logger = LogManager.getLogger(RedistribuicaoController.class);

    @PostMapping("/json")
    public ResponseEntity<List<CaixinhaResponse>> listar(
            @RequestParam("investimento") long investimento,
            @RequestBody @Valid List<CaixinhaRequest> caixinhas) {

        if (investimento < caixinhas.size()) {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }

        BigDecimal valorSobrou = new BigDecimal(investimento);

        List<CaixinhaResponse> caixinhasRedistribuidas = service
                .calculaDistribuicaoInvestimento(valorSobrou, caixinhas);

        return ResponseEntity.ok(caixinhasRedistribuidas);
    }

    @GetMapping
    public ResponseEntity<List<CaixinhaResponse>> listar(
            @RequestParam("investimento") long investimento,
            @RequestHeader("usuario") long usuarioId) {

        logger.debug("Investimento: {}, usuario: {}", investimento, usuarioId);
        try {
            BigDecimal valorSobrou = new BigDecimal(investimento);

            List<CaixinhaResponse> caixinhasRedistribuidas = service
                    .calculaDistribuicaoInvestimento(valorSobrou, usuarioId);

            return ResponseEntity.ok(caixinhasRedistribuidas);
        } catch (RuntimeException e) {
            logger.error("Erro ao calcular distribuição: {}", e.getMessage(), e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(List.of(CaixinhaResponse.builder().mensagem(e.getMessage()).build()));
        }
    }
}