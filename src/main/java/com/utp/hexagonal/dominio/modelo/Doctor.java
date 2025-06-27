package com.utp.hexagonal.dominio.modelo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {
    private Long id;
    private String nombre;
    private String especialidad;
}
