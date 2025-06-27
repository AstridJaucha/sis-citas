package com.utp.hexagonal.infraestructura.repository;

import com.utp.hexagonal.dominio.modelo.Doctor;
import com.utp.hexagonal.dominio.puertos.salida.DoctorSalida;
import com.utp.hexagonal.infraestructura.entity.DoctorEntity;
import com.utp.hexagonal.infraestructura.entity.EspecialidadEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DoctorJPARepositoryAdapter implements DoctorSalida {

    private final DoctorJPARepository doctorRepo;
    private final EspecialidadJPARepository especialidadRepo;

    public DoctorJPARepositoryAdapter(DoctorJPARepository doctorRepo, EspecialidadJPARepository especialidadRepo) {
        this.doctorRepo = doctorRepo;
        this.especialidadRepo = especialidadRepo;
    }

    @Override
    public Doctor guardar(Doctor doctor) {
        EspecialidadEntity especialidad = especialidadRepo.findByNombre(doctor.getEspecialidad())
                .orElseThrow(() -> new IllegalArgumentException("Especialidad no encontrada"));
        DoctorEntity entity = DoctorEntity.builder()
                .nombre(doctor.getNombre())
                .especialidad(especialidad)
                .build();
        return toModel(doctorRepo.save(entity));
    }

    @Override
    public List<Doctor> listar() {
        return doctorRepo.findAll().stream().map(this::toModel).collect(Collectors.toList());
    }

    @Override
    public List<Doctor> listarPorEspecialidad(String especialidad) {
        return doctorRepo.findByEspecialidadNombre(especialidad).stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    private Doctor toModel(DoctorEntity entity) {
        return Doctor.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .especialidad(entity.getEspecialidad().getNombre())
                .build();
    }
}
