package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.auth.repository.UsuarioRepository;
import org.example.exception.UsuarioNaoEncontradoException;
import org.example.model.entity.Usuario;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public Usuario getUsuario(long usuarioId) {

        Usuario usuario = repository.findByIdAndAtivoIsTrue(usuarioId);
        if (Objects.isNull(usuario)) {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado!");
        }
        return usuario;
    }
}
