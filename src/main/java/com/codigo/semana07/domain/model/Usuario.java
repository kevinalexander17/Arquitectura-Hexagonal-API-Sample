package com.codigo.semana07.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {

    private Long id;
    private String nombreUsuario;
    private String contrasenia;
    private String correoElectronico;
    private Persona persona;

    public Usuario() {
    }

    public Usuario(Long id, String nombreUsuario, String contrasenia, String correoElectronico, Persona persona) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.correoElectronico = correoElectronico;
        this.persona = persona;
    }

    public Long getId() {
        return id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public Persona getPersona() {
        return persona;
    }
}
