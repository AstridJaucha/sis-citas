package com.utp.hexagonal.infraestructura.repository;

import com.utp.hexagonal.infraestructura.entity.EspecialidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EspecialidadJPARepository extends JpaRepository<EspecialidadEntity, Long> {
    Optional<EspecialidadEntity> findByNombre(String nombre); // ya se usa en lógica de citas
}
