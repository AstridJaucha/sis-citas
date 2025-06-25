package com.utp.hexagonal.dominio.puertos.salida;

import com.utp.hexagonal.dominio.modelo.Paciente;

import java.util.Optional;
import java.util.List;

public interface PacienteSalida {
    Paciente guardarPaciente(Paciente paciente);
    Optional<Paciente> obtenerPorId(Long id);
    Optional<Paciente> obtenerPorDni(String dni);
    List<Paciente> listarTodos();
}
