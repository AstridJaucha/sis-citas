package com.utp.hexagonal.aplicacion.casodeuso;

import com.utp.hexagonal.dominio.modelo.Paciente;
import com.utp.hexagonal.dominio.puertos.entrada.PacienteEntrada;
import com.utp.hexagonal.dominio.puertos.salida.PacienteSalida;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteEntrada {

    private final PacienteSalida pacienteSalida;

    public PacienteServiceImpl(PacienteSalida pacienteSalida) {
        this.pacienteSalida = pacienteSalida;
    }

    @Override
    public Paciente registrarPaciente(Paciente paciente) {
        return pacienteSalida.guardarPaciente(paciente);
    }

    @Override
    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteSalida.obtenerPorId(id);
    }

    @Override
    public Optional<Paciente> buscarPorDni(String dni) {
        return pacienteSalida.obtenerPorDni(dni);
    }

    @Override
    public List<Paciente> listarPacientes() {
        return pacienteSalida.listarTodos();
    }

}
