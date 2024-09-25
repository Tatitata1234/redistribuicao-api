package org.example.auth.controller;

import jakarta.validation.Valid;
import org.example.auth.controller.request.UsuarioRequest;
import org.example.auth.controller.response.UsuarioResponse;
import org.example.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "https://tatitata1234.github.io/")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("login")
    public ResponseEntity<UsuarioResponse> login(@RequestBody @Valid UsuarioRequest request) {
        UsuarioResponse response = service.login(request);
        return ResponseEntity.ok(response);
    }
}
