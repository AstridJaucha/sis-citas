package com.utp.hexagonal.dominio.puertos.salida;

import com.utp.hexagonal.dominio.modelo.Persona;

import java.util.Optional;

public interface PersonaSalida {
    Persona crearPersona (Persona persona);

    Optional<Persona> getPersona(Long id);

    Optional<Persona> actualizarPersona (Long id, Persona persona);

    boolean borrarPersona(Long id);
}
