package com.codigo.semana07.application.usecase;

import com.codigo.semana07.domain.model.Persona;
import com.codigo.semana07.domain.ports.in.PersonaUseCase;
import com.codigo.semana07.domain.ports.out.PersonaRepositoryPort;

import java.util.Optional;

public class PersonaUseCaseImpl implements PersonaUseCase {

    private final PersonaRepositoryPort personaRepositoryPort;

    public PersonaUseCaseImpl(PersonaRepositoryPort personaRepositoryPort) {
        this.personaRepositoryPort = personaRepositoryPort;
    }

    @Override
    public Persona crearPersona(Persona persona) {
        return personaRepositoryPort.save(persona);
    }

    @Override
    public Optional<Persona> listarPersona(Long id) {
        return personaRepositoryPort.findById(id);
    }

    @Override
    public Optional<Persona> actualizarPersona(Persona persona, Long id) { return personaRepositoryPort.update(persona, id); }

    @Override
    public boolean eliminarPersona(Long id) {  return personaRepositoryPort.delete(id); }

}
