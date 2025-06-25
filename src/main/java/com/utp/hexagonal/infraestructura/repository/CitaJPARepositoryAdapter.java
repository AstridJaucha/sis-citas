package com.utp.hexagonal.infraestructura.repository;

import com.utp.hexagonal.dominio.modelo.Cita;
import com.utp.hexagonal.dominio.puertos.salida.CitaSalida;
import com.utp.hexagonal.infraestructura.entity.CitaEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CitaJPARepositoryAdapter implements CitaSalida {

    private final CitaJPARepository repository;

    public CitaJPARepositoryAdapter(CitaJPARepository repository) {
        this.repository = repository;
    }

    @Override
    public Cita guardarCita(Cita cita) {
        return repository.save(CitaEntity.delModelo(cita)).alModelo();
    }

    @Override
    public Optional<Cita> obtenerPorId(Long id) {
        return repository.findById(id).map(CitaEntity::alModelo);
    }

    @Override
    public List<Cita> listarTodas() {
        return repository.findAll().stream()
                .map(CitaEntity::alModelo)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cita> listarPorEspecialidad(String especialidad) {
        return repository.findByEspecialidad(especialidad).stream()
                .map(CitaEntity::alModelo)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cita> listarPorFecha(Date fecha) {
        return repository.findByFechaCita(fecha).stream()
                .map(CitaEntity::alModelo)
                .collect(Collectors.toList());
    }
}
