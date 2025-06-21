package com.utp.hexagonal.infraestructura.entity;

import com.utp.hexagonal.dominio.modelo.Persona;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "personas")
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 👈 permite que la BD genere el ID automáticamente
    private Long id;

    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String genero;

    public PersonaEntity() {
    }

    public PersonaEntity(Long id, String nombre, String apellido, Date fechaNacimiento, String genero) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }

    public static PersonaEntity delModeloDominio(Persona persona) {
        return new PersonaEntity(
                persona.getId(),
                persona.getNombre(),
                persona.getApellido(),
                persona.getFechaNacimiento(),
                persona.getGenero()
        );
    }

    public Persona alModeloDominio() {
        return new Persona(
                id,
                nombre,
                apellido,
                fechaNacimiento,
                genero
        );
    }
}
