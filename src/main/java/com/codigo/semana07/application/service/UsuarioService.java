package com.codigo.semana07.application.service;

import com.codigo.semana07.domain.model.Usuario;
import com.codigo.semana07.domain.ports.in.UsuarioUseCase;

import java.util.Optional;

public class UsuarioService implements UsuarioUseCase {

    private final UsuarioUseCase usuarioUseCase;

    public UsuarioService(UsuarioUseCase usuarioUseCase) {
        this.usuarioUseCase = usuarioUseCase;
    }

    @Override
    public Usuario createUsuario(Usuario u) {
        return usuarioUseCase.createUsuario(u);
    }

    @Override
    public Optional<Usuario> listarUsuario(Long id) {
        return usuarioUseCase.listarUsuario(id);
    }

    @Override
    public Optional<Usuario> updateUsuario(Usuario usuario) {
        return usuarioUseCase.updateUsuario(usuario);
    }

    @Override
    public boolean eliminarUsuario(Long id) {
        return usuarioUseCase.eliminarUsuario(id);
    }


}
