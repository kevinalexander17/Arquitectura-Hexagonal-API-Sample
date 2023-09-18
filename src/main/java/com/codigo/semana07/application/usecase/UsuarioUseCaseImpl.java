package com.codigo.semana07.application.usecase;

import com.codigo.semana07.domain.model.Usuario;
import com.codigo.semana07.domain.ports.in.UsuarioUseCase;
import com.codigo.semana07.domain.ports.out.UsuarioRepositoryPort;

import java.util.Optional;

public class UsuarioUseCaseImpl implements UsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public UsuarioUseCaseImpl(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public Usuario createUsuario(Usuario u) {
        return usuarioRepositoryPort.save(u);
    }

    @Override
    public Optional<Usuario> listarUsuario(Long id) {
        return usuarioRepositoryPort.findById(id);
    }

    @Override
    public Optional<Usuario> updateUsuario(Usuario usuario) {
        return usuarioRepositoryPort.update(usuario);
    }

    @Override
    public boolean eliminarUsuario(Long id) {
        return usuarioRepositoryPort.delete(id);
    }
}
