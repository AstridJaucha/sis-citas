package com.utp.hexagonal.infraestructura.repository;

import com.utp.hexagonal.infraestructura.entity.CitaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CitaJPARepository extends JpaRepository<CitaEntity, Long> {
    List<CitaEntity> findByEspecialidadNombre(String nombre);
    List<CitaEntity> findByFechaCita(LocalDate fecha);
    List<CitaEntity> findByPacienteId(Long pacienteId);

}
