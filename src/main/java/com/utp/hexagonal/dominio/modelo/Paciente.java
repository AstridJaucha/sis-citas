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
public class Paciente {
    private Long id;
    private String dni;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private String genero;
}
