package com.utp.hexagonal.infraestructura.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "doctores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "especialidad_id", nullable = false)
    private EspecialidadEntity especialidad;
}
