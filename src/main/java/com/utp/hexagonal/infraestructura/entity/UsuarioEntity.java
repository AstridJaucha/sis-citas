package com.utp.hexagonal.infraestructura.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String rol;

    @OneToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    private PacienteEntity paciente; // Relación directa con paciente
}
