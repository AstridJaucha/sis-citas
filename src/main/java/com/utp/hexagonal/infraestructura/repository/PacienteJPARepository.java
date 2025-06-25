package com.utp.hexagonal.infraestructura.repository;

import com.utp.hexagonal.infraestructura.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteJPARepository extends JpaRepository<PacienteEntity, Long> {
    Optional<PacienteEntity> findByDni(String dni);
}
