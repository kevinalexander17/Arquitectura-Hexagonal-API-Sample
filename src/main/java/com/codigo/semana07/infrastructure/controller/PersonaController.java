package com.codigo.semana07.infrastructure.controller;

import com.codigo.semana07.application.service.PersonaService;
import com.codigo.semana07.domain.model.Persona;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/personas")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }


    @PostMapping
    public ResponseEntity<Persona> createPersona(@RequestBody Persona persona) {
        Persona createdPersona = personaService.crearPersona(persona);
        return new ResponseEntity<>(createdPersona, HttpStatus.CREATED);
    }

    @GetMapping("/{personaId}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long personaId) {
        return personaService.listarPersona(personaId)
                .map(persona -> new ResponseEntity<>(persona, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/eliminar/{personaId}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long personaId) {
        if(personaService.eliminarPersona(personaId)){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
      }

    @PutMapping("/update/{personaId}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long personaId,@RequestBody Persona persona){
            return personaService.actualizarPersona(persona, personaId)
                    .map(personaActualizada -> new ResponseEntity<>(personaActualizada, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    }
