package com.utp.hexagonal.infraestructura.repository;

import com.utp.hexagonal.infraestructura.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorJPARepository extends JpaRepository<DoctorEntity, Long> {
    List<DoctorEntity> findByEspecialidadNombre(String nombre);
}
