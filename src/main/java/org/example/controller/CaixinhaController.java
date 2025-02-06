package org.example.controller;

import jakarta.validation.Valid;
import org.example.controller.request.CaixinhaRequest;
import org.example.model.entity.Caixinha;
import org.example.service.CaixinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caixinha")
@CrossOrigin(origins = "https://tatitata1234.github.io")
public class CaixinhaController {
    @Autowired
    private CaixinhaService caixinhaService;

    @PostMapping()
    public void edita(@RequestBody @Valid CaixinhaRequest caixinhas) {
        caixinhaService.edita(caixinhas);
    }
}
