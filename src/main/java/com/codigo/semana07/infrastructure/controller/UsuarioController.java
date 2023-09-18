package com.codigo.semana07.infrastructure.controller;

import com.codigo.semana07.application.service.UsuarioService;
import com.codigo.semana07.domain.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario){
    Usuario createdUsuario = usuarioService.createUsuario(usuario);
    return new ResponseEntity<>(createdUsuario, HttpStatus.CREATED);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Usuario> getUsuarioId(@PathVariable Long usuarioId){
        return  usuarioService.listarUsuario(usuarioId)
                .map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update")
    public ResponseEntity<Usuario> updatedUsuario(@RequestBody Usuario usuario){
       return usuarioService.updateUsuario(usuario)
               .map(usuarioActualizado -> new ResponseEntity<>(usuarioActualizado, HttpStatus.OK))
               .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{usuarioId}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long usuarioId){
        if(usuarioService.eliminarUsuario(usuarioId)){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
