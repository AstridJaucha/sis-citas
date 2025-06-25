package com.utp.hexagonal.infraestructura.entity;

import com.utp.hexagonal.dominio.modelo.Cita;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

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

    private Long pacienteId; // Relación simplificada

    private String especialidad;
    private String medico;

    private LocalDate fechaCita;
    private LocalTime horaCita;



    public static CitaEntity delModelo(Cita cita) {
        return new CitaEntity(
                cita.getId(), cita.getPacienteId(), cita.getEspecialidad(),
                cita.getMedico(), cita.getFechaCita(), cita.getHoraCita()
        );
    }

    public Cita alModelo() {
        return new Cita(id, pacienteId, especialidad, medico, fechaCita, horaCita);
    }
}
