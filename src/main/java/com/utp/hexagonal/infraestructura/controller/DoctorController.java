// DoctorController.java
package com.utp.hexagonal.infraestructura.controller;

import com.utp.hexagonal.dominio.modelo.Doctor;
import com.utp.hexagonal.dominio.puertos.entrada.DoctorEntrada;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctores")
@CrossOrigin(origins = "http://localhost:5173")
public class DoctorController {

    private final DoctorEntrada doctorEntrada;

    public DoctorController(DoctorEntrada doctorEntrada) {
        this.doctorEntrada = doctorEntrada;
    }

    @GetMapping
    public List<Doctor> listar() {
        return doctorEntrada.listarDoctores();
    }

    @GetMapping("/especialidad/{nombre}")
    public List<Doctor> listarPorEspecialidad(@PathVariable String nombre) {
        return doctorEntrada.listarPorEspecialidad(nombre);
    }
}
