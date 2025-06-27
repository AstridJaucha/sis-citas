package com.utp.hexagonal.infraestructura.repository;

import com.utp.hexagonal.dominio.modelo.Cita;
import com.utp.hexagonal.dominio.puertos.salida.CitaSalida;
import com.utp.hexagonal.infraestructura.entity.CitaEntity;
import com.utp.hexagonal.infraestructura.entity.EspecialidadEntity;
import com.utp.hexagonal.infraestructura.entity.PacienteEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CitaJPARepositoryAdapter implements CitaSalida {

    private final CitaJPARepository citaJPARepository;
    private final EspecialidadJPARepository especialidadJPARepository;
    private final PacienteJPARepository pacienteJPARepository;

    public CitaJPARepositoryAdapter(
            CitaJPARepository citaJPARepository,
            EspecialidadJPARepository especialidadJPARepository,
            PacienteJPARepository pacienteJPARepository) {
        this.citaJPARepository = citaJPARepository;
        this.especialidadJPARepository = especialidadJPARepository;
        this.pacienteJPARepository = pacienteJPARepository;
    }

    @Override
    public Cita guardarCita(Cita cita) {
        EspecialidadEntity especialidad = especialidadJPARepository
                .findByNombre(cita.getEspecialidad())
                .orElseThrow(() -> new IllegalArgumentException("Especialidad no encontrada"));

        PacienteEntity paciente = pacienteJPARepository
                .findById(cita.getPacienteId())
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado"));

        return citaJPARepository.save(CitaEntity.delModelo(cita, especialidad, paciente)).alModelo();
    }

    @Override
    public Optional<Cita> obtenerPorId(Long id) {
        return citaJPARepository.findById(id).map(CitaEntity::alModelo);
    }

    @Override
    public List<Cita> listarTodas() {
        return citaJPARepository.findAll().stream()
                .map(CitaEntity::alModelo)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cita> listarPorEspecialidad(String especialidad) {
        return citaJPARepository.findByEspecialidadNombre(especialidad).stream()
                .map(CitaEntity::alModelo)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cita> listarPorFecha(Date fecha) {
        LocalDate localDate = fecha.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        return citaJPARepository.findByFechaCita(localDate).stream()
                .map(CitaEntity::alModelo)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cita> listarPorPacienteId(Long pacienteId) {
        return citaJPARepository.findByPacienteId(pacienteId).stream()
                .map(CitaEntity::alModelo)
                .collect(Collectors.toList());
    }
}
