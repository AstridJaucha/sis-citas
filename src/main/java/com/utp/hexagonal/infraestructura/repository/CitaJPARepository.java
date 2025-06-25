package com.utp.hexagonal.infraestructura.repository;

import com.utp.hexagonal.infraestructura.entity.CitaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CitaJPARepository extends JpaRepository<CitaEntity, Long> {
    List<CitaEntity> findByEspecialidad(String especialidad);
    List<CitaEntity> findByFechaCita(Date fecha);
}
