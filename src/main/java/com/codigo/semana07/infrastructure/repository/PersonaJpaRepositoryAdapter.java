package com.codigo.semana07.infrastructure.repository;

import com.codigo.semana07.domain.model.Persona;
import com.codigo.semana07.domain.ports.out.PersonaRepositoryPort;
import com.codigo.semana07.infrastructure.entity.PersonaEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonaJpaRepositoryAdapter implements PersonaRepositoryPort {

    private final PersonaJpaRepository personaJpaRepository;

    public PersonaJpaRepositoryAdapter(PersonaJpaRepository personaJpaRepository) {
        this.personaJpaRepository = personaJpaRepository;
    }

    @Override
    public Persona save(Persona persona) {
        PersonaEntity personaEntity = PersonaEntity.fromDomainModel(persona);
        PersonaEntity savedPersonaEntity = personaJpaRepository.save(personaEntity);
        return savedPersonaEntity.toDomainModel();
    }

    @Override
    public Optional<Persona> findById(Long id) {
        return personaJpaRepository.findById(id).map(PersonaEntity::toDomainModel);
    }

    @Override
    public Optional<Persona> update(Persona persona, Long id) {
        if(personaJpaRepository.existsById(persona.getId())){
            PersonaEntity personaEntity = PersonaEntity.fromDomainModel(persona);
            PersonaEntity personaEntityUpdated = personaJpaRepository.save(personaEntity);
            return Optional.of(personaEntityUpdated.toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        if(personaJpaRepository.existsById(id)){
            personaJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
