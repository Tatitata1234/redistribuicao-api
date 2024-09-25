package org.example.repository;

import org.example.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Usuario findByIdAndAtivoIsTrue(long usuarioId);

    Usuario findByNomeUsuarioAndSenhaAndAtivoIsTrue(String nomeUsuario, String senha);
}
