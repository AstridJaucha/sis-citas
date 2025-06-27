package com.utp.hexagonal.infraestructura.controller;

import com.utp.hexagonal.dominio.modelo.Cita;
import com.utp.hexagonal.dominio.puertos.entrada.CitaEntrada;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaEntrada citaEntrada;

    public CitaController(CitaEntrada citaEntrada) {
        this.citaEntrada = citaEntrada;
    }

    @PostMapping
    public ResponseEntity<Cita> registrarCita(@RequestBody Cita cita) {
        return ResponseEntity.ok(citaEntrada.registrarCita(cita));
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<Cita>> listarPorPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(citaEntrada.listarPorPacienteId(id));
    }

    @GetMapping
    public ResponseEntity<List<Cita>> listarCitas() {
        return ResponseEntity.ok(citaEntrada.listarCitas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> buscarPorId(@PathVariable Long id) {
        return citaEntrada.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/especialidad/{esp}")
    public ResponseEntity<List<Cita>> listarPorEspecialidad(@PathVariable String esp) {
        return ResponseEntity.ok(citaEntrada.listarPorEspecialidad(esp));
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<Cita>> listarPorFecha(
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fecha) {
        return ResponseEntity.ok(citaEntrada.listarPorFecha(fecha));
    }
}
