package com.utp.hexagonal.infraestructura.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.utp.hexagonal.dominio.modelo.Persona;
import com.utp.hexagonal.aplicacion.service.PersonaService;


@RestController
@RequestMapping("api/personas")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping
    public ResponseEntity<Persona> create(@RequestBody Persona persona) {
        Persona createPerso = personaService.crearPersona(persona);
        return new ResponseEntity<>(createPerso, HttpStatus.CREATED);
    }

    @GetMapping("/{personaId}")
    public ResponseEntity<Persona> getPersona(@PathVariable Long personaId) {
        return personaService.getPersona(personaId)
                .map(persona -> new ResponseEntity<>(persona, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{personaId}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long personaId, @RequestBody Persona persona) {
        return personaService.actualizarPersona(personaId, persona)
                .map(per -> new ResponseEntity<>(per, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{personaId}")
    public ResponseEntity<Void> deletePersonaById(@PathVariable Long personaId) {
        if (personaService.deletePersona(personaId)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}


