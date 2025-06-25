package com.utp.hexagonal.dominio.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cita {
    private Long id;
    private Long pacienteId; // Se puede usar paciente directamente si quieres luego
    private String especialidad;
    private String medico;
    private Date fechaCita;
    private String horaCita;
}
