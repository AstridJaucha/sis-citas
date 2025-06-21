package com.utp.hexagonal.aplicacion.casodeuso;

import com.utp.hexagonal.dominio.puertos.entrada.PersonaEntrada;
import com.utp.hexagonal.dominio.modelo.Persona;
import com.utp.hexagonal.dominio.puertos.salida.PersonaSalida;

import java.util.Optional;

public class PersonaServiceImpl implements PersonaEntrada {
    private final PersonaSalida personaSalida;

    public PersonaServiceImpl(PersonaSalida personaSalida) {
        this.personaSalida = personaSalida;
    }

    @Override
    public Persona crearPersona(Persona persona) {
        return personaSalida.crearPersona(persona);
    }

    @Override
    public Optional<Persona> getPersona(Long id) {
        return personaSalida.getPersona(id);
    }

    @Override
    public Optional<Persona> actualizarPersona(Long id, Persona persona) {
        return personaSalida.actualizarPersona(id,persona);
    }

    @Override
    public boolean deletePersona(Long id) {
        return personaSalida.borrarPersona(id);
    }
}



