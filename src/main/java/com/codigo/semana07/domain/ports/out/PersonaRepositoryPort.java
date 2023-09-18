package com.codigo.semana07.domain.ports.out;

import com.codigo.semana07.domain.model.Persona;

import java.util.Optional;

public interface PersonaRepositoryPort {

    //crear persona
    Persona save(Persona persona);

    //listar persona
    Optional<Persona> findById(Long id);

    Optional<Persona> update(Persona persona, Long id);

    boolean delete(Long id);
}
