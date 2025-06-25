package com.utp.hexagonal.infraestructura.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReniecResponseDTO {
    private String dni;
    private String nombres;
    private String apellidos;
    private String fechaNacimiento; // formato: dd/MM/yyyy
    private String sexo;
}
