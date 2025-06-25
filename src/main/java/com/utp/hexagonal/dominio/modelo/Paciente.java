package com.utp.hexagonal.dominio.modelo;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    private Long id;
    @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 dígitos")
    private String dni;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private String genero;
}
