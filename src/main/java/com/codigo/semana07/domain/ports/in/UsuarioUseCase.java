package com.codigo.semana07.domain.ports.in;

import com.codigo.semana07.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioUseCase {
    Usuario createUsuario (Usuario u);
    Optional<Usuario> listarUsuario(Long id);
    Optional<Usuario> updateUsuario(Usuario usuario);
    boolean eliminarUsuario(Long id);
}
