package com.utp.hexagonal.infraestructura.repository;

import com.utp.hexagonal.infraestructura.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaJPARepository extends JpaRepository<PersonaEntity, Long> {
}
