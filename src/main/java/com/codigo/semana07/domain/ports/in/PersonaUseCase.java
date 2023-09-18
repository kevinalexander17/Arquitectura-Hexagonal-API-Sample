package com.codigo.semana07.domain.ports.in;


import com.codigo.semana07.domain.model.Persona;

import java.util.Optional;

public interface PersonaUseCase {
    Persona crearPersona(Persona persona);
    Optional<Persona> listarPersona(Long id);
    Optional<Persona> actualizarPersona(Persona persona,Long id);
    boolean eliminarPersona(Long id);
}
