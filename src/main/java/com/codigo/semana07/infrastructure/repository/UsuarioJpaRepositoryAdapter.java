package com.codigo.semana07.infrastructure.repository;

import com.codigo.semana07.domain.model.Usuario;
import com.codigo.semana07.domain.ports.out.UsuarioRepositoryPort;
import com.codigo.semana07.infrastructure.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioJpaRepositoryAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository usuarioJpaRepository;

    public UsuarioJpaRepositoryAdapter(UsuarioJpaRepository usuarioJpaRepository) {
        this.usuarioJpaRepository = usuarioJpaRepository;
    }

    @Override
    public Usuario save(Usuario u) {
        UsuarioEntity usuarioEntity = UsuarioEntity.fromDomainModel(u);
        UsuarioEntity savedUsuarioEntity = usuarioJpaRepository.save(usuarioEntity);
        return  savedUsuarioEntity.toDomainModel();
    }

    @Override
    public Optional<Usuario> findById(Long id) {

        return usuarioJpaRepository.findById(id).map(UsuarioEntity::toDomainModel);
    }

    @Override
    public Optional<Usuario> update(Usuario usuario) {

        if(usuarioJpaRepository.existsById(usuario.getId())){
            UsuarioEntity usuarioEntity = UsuarioEntity.fromDomainModel(usuario);
            UsuarioEntity updatedUsuarioEntity = usuarioJpaRepository.save(usuarioEntity);
            return Optional.of(updatedUsuarioEntity.toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        if(usuarioJpaRepository.existsById(id)){
            usuarioJpaRepository.deleteById(id);
        }
        return false;
    }
}
