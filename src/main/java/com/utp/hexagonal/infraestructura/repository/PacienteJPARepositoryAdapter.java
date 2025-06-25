package com.utp.hexagonal.infraestructura.repository;

import com.utp.hexagonal.dominio.modelo.Paciente;
import com.utp.hexagonal.dominio.puertos.salida.PacienteSalida;
import com.utp.hexagonal.infraestructura.entity.PacienteEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PacienteJPARepositoryAdapter implements PacienteSalida {

    private final PacienteJPARepository repository;

    public PacienteJPARepositoryAdapter(PacienteJPARepository repository) {
        this.repository = repository;
    }

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        return repository.save(PacienteEntity.delModelo(paciente)).alModelo();
    }

    @Override
    public Optional<Paciente> obtenerPorId(Long id) {
        return repository.findById(id).map(PacienteEntity::alModelo);
    }

    @Override
    public Optional<Paciente> obtenerPorDni(String dni) {
        return repository.findByDni(dni).map(PacienteEntity::alModelo);
    }

    @Override
    public List<Paciente> listarTodos() {
        return repository.findAll().stream()
                .map(PacienteEntity::alModelo)
                .collect(Collectors.toList());
    }
}
