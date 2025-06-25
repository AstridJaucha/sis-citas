/*

package com.utp.hexagonal.aplicacion.service;


import java.util.Optional;

public class PersonaService implements PersonaEntrada {

    private final PersonaEntrada personaEntrada;

    // Constructor que recibe la implementación
    public PersonaService(PersonaEntrada personaEntrada) {
        this.personaEntrada = personaEntrada;
    }

    @Override
    public Persona crearPersona(Persona persona) {
        return personaEntrada.crearPersona(persona);
    }

    @Override
    public Optional<Persona> getPersona(Long id) {
        return personaEntrada.getPersona(id);
    }

    @Override
    public Optional<Persona> actualizarPersona(Long id, Persona persona) {
        return personaEntrada.actualizarPersona(id, persona);
    }

    @Override
    public boolean deletePersona(Long id) {
        return personaEntrada.deletePersona(id);
    }
}

 */