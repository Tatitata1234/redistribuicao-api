package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.example.Caixinha.VALOR_MINIMO;


@RestController
@RequestMapping("/caixinhas")
@CrossOrigin(origins = "http://localhost:3000")
public class CaixinhaController {

    @Autowired
    private CaixinhaService caixinhaService;

    @GetMapping
    public ResponseEntity<List<CaixinhaResponse>> listar(@RequestParam("investimento") int investimento) {
        BigDecimal valorSobrou = new BigDecimal(investimento);
        List<CaixinhaResponse> caixinhas = caixinhaService.calculaDitribuicaoInvestimento(valorSobrou);
        return ResponseEntity.ok(caixinhas);
    }


}