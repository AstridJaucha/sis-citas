package com.utp.hexagonal.infraestructura.repository;

import com.utp.hexagonal.dominio.modelo.Especialidad;
import com.utp.hexagonal.dominio.puertos.salida.EspecialidadSalida;
import com.utp.hexagonal.infraestructura.entity.EspecialidadEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EspecialidadJPARepositoryAdapter implements EspecialidadSalida {

    private final EspecialidadJPARepository repository;

    public EspecialidadJPARepositoryAdapter(EspecialidadJPARepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Especialidad> listarTodas() {
        return repository.findAll().stream()
                .map(EspecialidadEntity::alModelo)
                .collect(Collectors.toList());
    }
}
