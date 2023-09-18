package com.codigo.semana07.infrastructure.repository;

import com.codigo.semana07.domain.model.Persona;
import com.codigo.semana07.infrastructure.entity.PersonaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PersonaJpaRepositoryAdapterTest {

    @Mock
    PersonaJpaRepository personaJpaRepository;

    @InjectMocks
    PersonaJpaRepositoryAdapter personaJpaRepositoryAdapter;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        personaJpaRepositoryAdapter = new PersonaJpaRepositoryAdapter(personaJpaRepository);
    }

    @Test
    void saveApersonSuccessfully(){
        Persona persona = new Persona(1L,"Alexander","Roman",new Date(),"Masculino");
        PersonaEntity personaEntity = new PersonaEntity(1L,"Alexander","Roman",new Date(),"Masculino");
        when(personaJpaRepository.save(Mockito.any(PersonaEntity.class))).thenReturn(personaEntity);
        Persona personaAdapter = personaJpaRepositoryAdapter.save(persona);

        assertNotNull(personaAdapter);
        assertEquals(persona.getId(), personaAdapter.getId());
        assertEquals(persona.getNombre(), personaAdapter.getNombre());

    }
    @Test
    void findApersonByIdSuccessfully(){
        Long id = 1L;
        PersonaEntity personaEntity = new PersonaEntity(1L,"Alexander","Roman",new Date(),"Masculino");
        when(personaJpaRepository.findById(id)).thenReturn(Optional.of(personaEntity));
        Optional<Persona> personaAdapter = personaJpaRepositoryAdapter.findById(id);
        assertNotNull(personaAdapter);
        personaAdapter.map(persona -> {
            Long personaId = persona.getId();
            assertEquals(personaId, id);
            return  personaId;
        });
    }
    @Test
    void findByIdIsEmpty(){
        Long id = 1L;
        when(personaJpaRepository.findById(id)).thenReturn(Optional.empty());
        Optional<Persona> personaAdapter = personaJpaRepositoryAdapter.findById(id);
        assertTrue(personaAdapter.isEmpty());
    }
    @Test
    void updateApersonSuccessfully(){
        Long id = 1L;
        Persona persona = new Persona(1L,"Alexander","Roman",new Date(),"Masculino");
        PersonaEntity personaEntity = new PersonaEntity(1L,"Alexander","Quispe",new Date(),"Masculino");
        when(personaJpaRepository.existsById(id)).thenReturn(true);
        when(personaJpaRepository.save(Mockito.any(PersonaEntity.class))).thenReturn(personaEntity);
        Optional<Persona> personaAdapter = personaJpaRepositoryAdapter.update(persona, id);
        assertNotNull(personaAdapter);
        assertEquals(persona.getId(), personaAdapter.get().getId());
        assertEquals(persona.getNombre(), personaAdapter.get().getNombre());

    }

    @Test
    void deleteApersonSuccessfully(){
        Long id = 1L;
        when(personaJpaRepository.existsById(id)).thenReturn(true);
        boolean personaAdapter = personaJpaRepositoryAdapter.delete(id);
        assertTrue(personaAdapter);
    }

    @Test
    void updateApersonIsEmpty(){
        Long id = 1L;
        Persona persona = new Persona();
       PersonaEntity personaEntity = new PersonaEntity();
        when(personaJpaRepository.existsById(id)).thenReturn(false);
        Optional<Persona> personaAdapter = personaJpaRepositoryAdapter.update(persona, id);
        assertTrue(personaAdapter.isEmpty());
    }

    @Test
    void deleteApersonWhoDoesNotExist(){
        Long id = 1L;
        when(personaJpaRepository.existsById(id)).thenReturn(false);
        boolean personaAdapter = personaJpaRepositoryAdapter.delete(id);
        assertFalse(personaAdapter);
    }
}