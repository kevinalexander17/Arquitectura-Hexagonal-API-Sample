package com.codigo.semana07.domain.ports.out;

import com.codigo.semana07.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioRepositoryPort {

    Usuario save(Usuario u);
    Optional<Usuario> findById(Long id);
    Optional<Usuario> update(Usuario usuario);
    boolean delete(Long id);
}
