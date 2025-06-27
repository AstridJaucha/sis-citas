package com.utp.hexagonal.dominio.modelo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    private Long id;
    private String username;
    private String password;
    private String rol;
    private Long pacienteId; // opcional según el rol
}
