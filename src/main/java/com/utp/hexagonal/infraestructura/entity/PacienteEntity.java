package com.utp.hexagonal.infraestructura.entity;

import com.utp.hexagonal.dominio.modelo.Paciente;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pacientes")
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dni;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private String genero;

    public static PacienteEntity delModelo(Paciente paciente) {
        return new PacienteEntity(
                paciente.getId(), paciente.getDni(), paciente.getNombres(),
                paciente.getApellidos(), paciente.getFechaNacimiento(), paciente.getGenero()
        );
    }

    public Paciente alModelo() {
        return new Paciente(id, dni, nombres, apellidos, fechaNacimiento, genero);
    }
}
