package com.codigo.semana07.infrastructure.config;

import com.codigo.semana07.application.service.PersonaService;
import com.codigo.semana07.application.service.UsuarioService;
import com.codigo.semana07.application.usecase.PersonaUseCaseImpl;
import com.codigo.semana07.application.usecase.UsuarioUseCaseImpl;
import com.codigo.semana07.domain.ports.out.PersonaRepositoryPort;
import com.codigo.semana07.domain.ports.out.UsuarioRepositoryPort;
import com.codigo.semana07.infrastructure.repository.PersonaJpaRepositoryAdapter;
import com.codigo.semana07.infrastructure.repository.UsuarioJpaRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public PersonaService personaService(PersonaRepositoryPort personaRepositoryPort){
        return new PersonaService(new PersonaUseCaseImpl(personaRepositoryPort));
    }

    @Bean
    public PersonaRepositoryPort personaRepositoryPort (PersonaJpaRepositoryAdapter personaJpaRepositoryAdapter){
        return personaJpaRepositoryAdapter;
    }

    @Bean
    public UsuarioService usuarioService (UsuarioRepositoryPort usuarioRepositoryPort){
        return  new UsuarioService(new UsuarioUseCaseImpl(usuarioRepositoryPort));
    }

    @Bean
    public UsuarioRepositoryPort usuarioRepositoryPort(UsuarioJpaRepositoryAdapter usuarioJpaRepositoryAdapter){
        return usuarioJpaRepositoryAdapter;
    }
}
