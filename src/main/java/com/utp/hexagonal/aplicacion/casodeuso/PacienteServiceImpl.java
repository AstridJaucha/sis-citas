package com.utp.hexagonal.aplicacion.casodeuso;

import com.utp.hexagonal.dominio.modelo.Paciente;
import com.utp.hexagonal.dominio.modelo.Usuario;
import com.utp.hexagonal.dominio.puertos.entrada.PacienteEntrada;
import com.utp.hexagonal.dominio.puertos.salida.PacienteSalida;
import com.utp.hexagonal.dominio.puertos.salida.UsuarioSalida;
import com.utp.hexagonal.infraestructura.client.ReniecClient;
import com.utp.hexagonal.infraestructura.dto.ReniecResponseDTO;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public class PacienteServiceImpl implements PacienteEntrada {

    private final PacienteSalida pacienteSalida;
    private final ReniecClient reniecClient;
    private final UsuarioSalida usuarioSalida;

    public PacienteServiceImpl(PacienteSalida pacienteSalida, ReniecClient reniecClient, UsuarioSalida usuarioSalida) {
        this.pacienteSalida = pacienteSalida;
        this.reniecClient = reniecClient;
        this.usuarioSalida = usuarioSalida;
    }

    @Override
    public Paciente registrarPaciente(Paciente paciente) {
        Optional<Paciente> existente = pacienteSalida.buscarPorDni(paciente.getDni());
        if (existente.isPresent()) return existente.get();

        ReniecResponseDTO dataReniec = reniecClient.consultarPorDni(paciente.getDni());
        if (dataReniec == null || dataReniec.getNombres() == null) {
            throw new IllegalArgumentException("No se pudo obtener información del DNI desde RENIEC.");
        }

        paciente.setNombres(dataReniec.getNombres());
        paciente.setApellidos(dataReniec.getApellidos());
        paciente.setGenero(dataReniec.getSexo());

        try {
            Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(dataReniec.getFechaNacimiento());
            paciente.setFechaNacimiento(fecha);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al convertir la fecha de nacimiento del RENIEC.");
        }

        Paciente guardado = pacienteSalida.guardarPaciente(paciente);

        // Crear usuario asociado con rol PACIENTE
        Usuario usuario = Usuario.builder()
                .username(guardado.getDni())
                .password(guardado.getDni())
                .rol("PACIENTE")
                .pacienteId(guardado.getId())
                .build();

        usuarioSalida.guardar(usuario);

        return guardado;
    }

    @Override
    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteSalida.buscarPorId(id);
    }

    @Override
    public Optional<Paciente> buscarPorDni(String dni) {
        return pacienteSalida.buscarPorDni(dni);
    }

    @Override
    public List<Paciente> listarPacientes() {
        return pacienteSalida.listarTodos();
    }
}
