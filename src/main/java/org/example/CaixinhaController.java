package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/caixinhas")
@CrossOrigin(origins = "http://localhost:4000")
public class CaixinhaController {

    @Autowired
    private CaixinhaService caixinhaService;

    @GetMapping
    public ResponseEntity<List<CaixinhaResponse>> listar(@RequestParam("investimento") long investimento) {
        if (investimento < 26) {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
        BigDecimal valorSobrou = new BigDecimal(investimento);
        List<CaixinhaResponse> caixinhas = caixinhaService.calculaDitribuicaoInvestimento(valorSobrou);
        return ResponseEntity.ok(caixinhas);
    }


}