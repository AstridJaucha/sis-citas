package com.utp.hexagonal.aplicacion.casodeuso;

import com.utp.hexagonal.dominio.modelo.Cita;
import com.utp.hexagonal.dominio.puertos.entrada.CitaEntrada;
import com.utp.hexagonal.dominio.puertos.salida.CitaSalida;
import com.utp.hexagonal.dominio.puertos.salida.PacienteSalida;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImpl implements CitaEntrada {

    private final CitaSalida citaSalida;
    private final PacienteSalida pacienteSalida;

    public CitaServiceImpl(CitaSalida citaSalida, PacienteSalida pacienteSalida) {
        this.citaSalida = citaSalida;
        this.pacienteSalida = pacienteSalida;
    }

    @Override
    public Cita registrarCita(Cita cita) {
        // Validar que el paciente exista
        pacienteSalida.buscarPorId(cita.getPacienteId())
                .orElseThrow(() -> new IllegalArgumentException("El paciente no existe"));

        return citaSalida.guardarCita(cita);
    }


    @Override
    public Optional<Cita> buscarPorId(Long id) {
        return citaSalida.obtenerPorId(id);
    }

    @Override
    public List<Cita> listarCitas() {
        return citaSalida.listarTodas();
    }

    @Override
    public List<Cita> listarPorEspecialidad(String especialidad) {
        return citaSalida.listarPorEspecialidad(especialidad);
    }

    @Override
    public List<Cita> listarPorFecha(Date fecha) {
        return citaSalida.listarPorFecha(fecha);
    }
}
