package com.codigo.semana07.infrastructure.repository;

import com.codigo.semana07.domain.model.Persona;
import com.codigo.semana07.domain.model.Usuario;
import com.codigo.semana07.infrastructure.entity.PersonaEntity;
import com.codigo.semana07.infrastructure.entity.UsuarioEntity;
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

class UsuarioJpaRepositoryAdapterTest {

    @Mock
    UsuarioJpaRepository usuarioJpaRepository;

    @InjectMocks
    UsuarioJpaRepositoryAdapter usuarioJpaRepositoryAdapter;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        usuarioJpaRepositoryAdapter = new UsuarioJpaRepositoryAdapter(usuarioJpaRepository);
    }

    @Test
    void saveAuserSuccessfully(){
        Persona persona = new Persona(2L, "Alexander", "Roman", new Date(), "Masculino");
        Usuario usuario = new Usuario(1L,
                            "Alesso",
                                "123456",
                            "qalesso370@gmail.com",
                                            persona);
        PersonaEntity personaEntity = new PersonaEntity(2L, "Alexander", "Roman", new Date(), "Masculino");
        UsuarioEntity usuarioEntity = new UsuarioEntity(1L,
                                        "Alesso",
                                            "123456",
                                        "qalesso370@gmail.com",
                                                        personaEntity);
        when(usuarioJpaRepository.save(Mockito.any(UsuarioEntity.class))).thenReturn(usuarioEntity);
        Usuario usuarioAdapter = usuarioJpaRepositoryAdapter.save(usuario);

        assertNotNull(usuarioAdapter);
        assertEquals(usuario.getId(), usuarioAdapter.getId());
        assertEquals(usuario.getNombreUsuario(), usuarioAdapter.getNombreUsuario());
        assertEquals(usuario.getPersona().getId(), usuarioAdapter.getPersona().getId());
        assertEquals(usuario.getPersona().getNombre(), usuarioAdapter.getPersona().getNombre());
    }

    @Test
    void findAuserByIdSuccessfully(){
        Long id = 1L;
        String nombre_Persona = "Alexander";
        Long persona_Id = 2L;
        PersonaEntity personaEntity = new PersonaEntity(2L, "Alexander", "Roman", new Date(), "Masculino");
        UsuarioEntity usuarioEntity = new UsuarioEntity(1L,
                                            "Alesso",
                                                "123456",
                                            "qalesso370@gmail.com",
                                                            personaEntity);
        when(usuarioJpaRepository.findById(id)).thenReturn(Optional.of(usuarioEntity));
        Optional<Usuario> usuarioAdapter = usuarioJpaRepositoryAdapter.findById(id);
        assertNotNull(usuarioAdapter);
        usuarioAdapter.map(usuario -> {
           Long usuarioId = usuario.getId();
           Long personaId = usuario.getPersona().getId();
           String nombrePersona = usuario.getPersona().getNombre();
           assertEquals(usuarioId, id);
            assertEquals(personaId,persona_Id);
            assertEquals(nombrePersona,nombre_Persona);
           return usuarioId;
        });
    }

    @Test
    void updateAuserSuccessfully(){
        Long id = 1L;
        Persona persona = new Persona(2L, "Alexander", "Roman", new Date(), "Masculino");
        Usuario usuario = new Usuario(1L,
                        "Alesso",
                            "123456",
                        "qalesso370@gmail.com",
                                       persona);
        PersonaEntity personaEntity = new PersonaEntity(2L, "Alexander", "Roman", new Date(), "Masculino");
        UsuarioEntity usuarioEntity = new UsuarioEntity(1L,
                        "Alesso",
                            "123456",
                        "qalesso370@gmail.com",
                                        personaEntity);

        when(usuarioJpaRepository.existsById(id)).thenReturn(true);
        when(usuarioJpaRepository.save(Mockito.any(UsuarioEntity.class))).thenReturn(usuarioEntity);
        Optional<Usuario> usuarioAdapter = usuarioJpaRepositoryAdapter.update(usuario);
        assertNotNull(usuarioAdapter);
        assertEquals(usuario.getId(), usuarioAdapter.get().getId());
        assertEquals(usuario.getContrasenia(), usuarioAdapter.get().getContrasenia());
        assertEquals(usuario.getCorreoElectronico(), usuarioAdapter.get().getCorreoElectronico());
        assertEquals(usuario.getNombreUsuario(), usuarioAdapter.get().getNombreUsuario());
    }

    @Test
    void findByIdIsEmpty(){
        Long id = 1L;
        when(usuarioJpaRepository.findById(id)).thenReturn(Optional.empty());
        Optional<Usuario> usuarioAdapter = usuarioJpaRepositoryAdapter.findById(id);
        assertTrue(usuarioAdapter.isEmpty());
    }

    @Test
    void updateAuserIsEmpty(){
        Long id = 1L;
        Usuario usuario = new Usuario();
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        when(usuarioJpaRepository.existsById(id)).thenReturn(false);
        Optional<Usuario> usuarioAdapter = usuarioJpaRepositoryAdapter.update(usuario);
        assertTrue(usuarioAdapter.isEmpty());
    }

    /*@Test
    void deleteAuserSuccessfully(){
        Long id = 1L;
        when(usuarioJpaRepository.existsById(id)).thenReturn(true);
        boolean usuarioAdapter = usuarioJpaRepositoryAdapter.delete(id);
        assertTrue(usuarioAdapter);
    }*/

    @Test
    void deleteApersonWhoDoesNotExist(){
        Long id = 1L;
        when(usuarioJpaRepository.existsById(id)).thenReturn(false);
        boolean usuarioAdapter = usuarioJpaRepositoryAdapter.delete(id);
        assertFalse(usuarioAdapter,"No se puede eliminar un usuario que no existe");
    }
}

