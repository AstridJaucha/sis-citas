package com.utp.hexagonal.infraestructura.controller;

import com.utp.hexagonal.infraestructura.entity.EspecialidadEntity;
import com.utp.hexagonal.infraestructura.repository.EspecialidadJPARepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
@CrossOrigin(origins = "*") // Opcional: permite acceso desde cualquier origen (útil para frontend en desarrollo)
public class EspecialidadController {

    private final EspecialidadJPARepository especialidadRepo;

    public EspecialidadController(EspecialidadJPARepository especialidadRepo) {
        this.especialidadRepo = especialidadRepo;
    }

    @GetMapping
    public List<EspecialidadEntity> listarEspecialidades() {
        return especialidadRepo.findAll();
    }
}
