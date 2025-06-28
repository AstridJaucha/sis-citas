package com.utp.hexagonal.infraestructura.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class CitaDTO {
    private Long id;
    private String dniPaciente;
    private String especialidad;
    private String medico;
    private LocalDate fechaCita;
    private LocalTime horaCita;
}
