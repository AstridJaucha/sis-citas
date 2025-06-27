package com.utp.hexagonal.dominio.puertos.entrada;

import com.utp.hexagonal.dominio.modelo.Cita;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CitaEntrada {
    Cita registrarCita(Cita cita);
    Optional<Cita> buscarPorId(Long id);
    List<Cita> listarCitas();
    List<Cita> listarPorEspecialidad(String especialidad);
    List<Cita> listarPorFecha(Date fecha);
    List<Cita> listarPorPacienteId(Long pacienteId);

}
