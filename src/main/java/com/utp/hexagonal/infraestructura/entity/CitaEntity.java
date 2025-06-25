package com.utp.hexagonal.infraestructura.entity;

import com.utp.hexagonal.dominio.modelo.Cita;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "citas")
public class CitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private PacienteEntity paciente;

    @ManyToOne
    @JoinColumn(name = "especialidad_id", nullable = false)
    private EspecialidadEntity especialidad;

    private String medico;
    private LocalDate fechaCita;
    private LocalTime horaCita;

    public static CitaEntity delModelo(Cita cita, EspecialidadEntity especialidad, PacienteEntity paciente) {
        return new CitaEntity(
                cita.getId(),
                paciente,
                especialidad,
                cita.getMedico(),
                cita.getFechaCita(),
                cita.getHoraCita()
        );
    }

    public Cita alModelo() {
        return new Cita(
                id,
                paciente.getId(),
                especialidad.getNombre(),
                medico,
                fechaCita,
                horaCita
        );
    }
}
