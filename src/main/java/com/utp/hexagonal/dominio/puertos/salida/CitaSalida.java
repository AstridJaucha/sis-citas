package com.utp.hexagonal.dominio.puertos.salida;

import com.utp.hexagonal.dominio.modelo.Cita;

import java.util.List;
import java.util.Optional;

public interface CitaSalida {
    Cita guardarCita(Cita cita);
    Optional<Cita> obtenerPorId(Long id);
    List<Cita> listarTodas();
    List<Cita> listarPorEspecialidad(String especialidad);
    List<Cita> listarPorFecha(java.util.Date fecha);
}
