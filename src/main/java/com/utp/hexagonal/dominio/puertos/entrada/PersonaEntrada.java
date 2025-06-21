package com.utp.hexagonal.dominio.puertos.entrada;

import com.utp.hexagonal.dominio.modelo.Persona;

import java.util.Optional;

public interface PersonaEntrada {
    Persona crearPersona (Persona persona);

    Optional<Persona> getPersona(Long id);

    Optional<Persona> actualizarPersona (Long id, Persona persona);

    boolean deletePersona (Long id);

}
