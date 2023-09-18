package com.codigo.semana07.infrastructure.entity;

import com.codigo.semana07.domain.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "usuarios")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreUsuario;
    private String contrasenia;
    private String correoElectronico;

    @OneToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private PersonaEntity persona;


    public UsuarioEntity() { }

    public UsuarioEntity(Long id, String nombreUsuario, String contrasenia, String correoElectronico, PersonaEntity persona) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.correoElectronico = correoElectronico;
        this.persona = persona;
    }

    public static UsuarioEntity fromDomainModel (Usuario usuario){
        return new UsuarioEntity(
            usuario.getId(),
            usuario.getNombreUsuario(),
            usuario.getContrasenia(),
            usuario.getCorreoElectronico(),
            PersonaEntity.fromDomainModel(usuario.getPersona())
        );
    }

    public Usuario toDomainModel(){
        return new Usuario(id, nombreUsuario,contrasenia,
                correoElectronico, persona.toDomainModel());}
}
