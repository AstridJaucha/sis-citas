package com.utp.hexagonal.infraestructura.entity;

import com.utp.hexagonal.dominio.modelo.Especialidad;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "especialidades")
public class EspecialidadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    // Métodos para conversión (opcional si trabajas con modelo dominio)
    public Especialidad alModelo() {
        return new Especialidad(id, nombre);
    }

    public static EspecialidadEntity delModelo(Especialidad e) {
        return new EspecialidadEntity(e.getId(), e.getNombre());
    }
}
