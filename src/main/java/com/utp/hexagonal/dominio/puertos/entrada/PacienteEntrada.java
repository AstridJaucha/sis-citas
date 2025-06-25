package com.utp.hexagonal.dominio.puertos.entrada;

import com.utp.hexagonal.dominio.modelo.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteEntrada {
    Paciente registrarPaciente(Paciente paciente);
    Optional<Paciente> buscarPorId(Long id);
    Optional<Paciente> buscarPorDni(String dni);
    List<Paciente> listarPacientes();
}
