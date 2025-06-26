package com.utp.hexagonal.aplicacion.casodeuso;

import com.utp.hexagonal.dominio.modelo.Paciente;
import com.utp.hexagonal.dominio.puertos.entrada.PacienteEntrada;
import com.utp.hexagonal.dominio.puertos.salida.PacienteSalida;
import com.utp.hexagonal.infraestructura.client.ReniecClient;
import com.utp.hexagonal.infraestructura.dto.ReniecResponseDTO;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteEntrada {

    private final PacienteSalida pacienteSalida;
    private final ReniecClient reniecClient;

    public PacienteServiceImpl(PacienteSalida pacienteSalida, ReniecClient reniecClient) {
        this.pacienteSalida = pacienteSalida;
        this.reniecClient = reniecClient;
    }

    @Override
    public Paciente registrarPaciente(Paciente paciente) {
        // Verificar si ya existe en la base de datos
        Optional<Paciente> existente = pacienteSalida.buscarPorDni(paciente.getDni());
        if (existente.isPresent()) {
            // Ya existe, devolvemos el paciente encontrado
            return existente.get();
        }

        // No existe: consultar API RENIEC
        ReniecResponseDTO dataReniec = reniecClient.consultarPorDni(paciente.getDni());
        if (dataReniec == null || dataReniec.getNombres() == null) {
            throw new IllegalArgumentException("No se pudo obtener información del DNI desde RENIEC.");
        }

        // Completar datos con los datos de RENIEC
        paciente.setNombres(dataReniec.getNombres());
        paciente.setApellidos(dataReniec.getApellidos()); // Ya combinaste apellidos
        paciente.setGenero(dataReniec.getSexo());

        try {
            Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(dataReniec.getFechaNacimiento());
            paciente.setFechaNacimiento(fecha);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al convertir la fecha de nacimiento del RENIEC.");
        }

        // Guardar y devolver
        return pacienteSalida.guardarPaciente(paciente);
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
