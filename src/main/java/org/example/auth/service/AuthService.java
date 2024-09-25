package org.example.auth.service;

import org.example.auth.controller.request.UsuarioRequest;
import org.example.auth.controller.response.UsuarioResponse;
import org.example.auth.mapper.UsuarioMapper;
import org.example.model.entity.Usuario;
import org.example.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UsuarioRepository repository;

    public UsuarioResponse login(UsuarioRequest request) {
        Usuario usuario = repository.findByNomeUsuarioAndSenhaAndAtivoIsTrue(request.getNomeUsuario(), request.getSenha());
        return UsuarioMapper.toResponse(usuario);
    }
}
