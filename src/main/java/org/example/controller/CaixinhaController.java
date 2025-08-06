package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.controller.request.CaixinhaRequest;
import org.example.service.CaixinhaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/caixinha")
@CrossOrigin(origins = "https://tatitata1234.github.io")
@RequiredArgsConstructor
public class CaixinhaController {

    private final CaixinhaService caixinhaService;

    @PostMapping()
    public void edita(@RequestBody @Valid CaixinhaRequest caixinhas) {
        caixinhaService.edita(caixinhas);
    }
}
