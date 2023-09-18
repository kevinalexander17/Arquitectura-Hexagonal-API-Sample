package com.codigo.semana07.application.service;

import com.codigo.semana07.domain.model.Persona;
import com.codigo.semana07.domain.ports.in.PersonaUseCase;

import java.util.Optional;

public class PersonaService implements PersonaUseCase {

    private final PersonaUseCase personaUseCase;

    public PersonaService(PersonaUseCase personaUseCase) {
        this.personaUseCase = personaUseCase;
    }

    @Override
    public Persona crearPersona(Persona persona) {
        return personaUseCase.crearPersona(persona);
    }

    @Override
    public Optional<Persona> listarPersona(Long id) {
        return personaUseCase.listarPersona(id);
    }

    @Override
    public Optional<Persona> actualizarPersona(Persona persona, Long id) { return personaUseCase.actualizarPersona(persona, id);}

    @Override
    public boolean eliminarPersona(Long id) { return personaUseCase.eliminarPersona(id);}
}
