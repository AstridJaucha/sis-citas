package com.utp.hexagonal.infraestructura.controller;

import com.utp.hexagonal.dominio.modelo.Cita;
import com.utp.hexagonal.dominio.puertos.entrada.CitaEntrada;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaEntrada citaService;

    public CitaController(CitaEntrada citaService) {
        this.citaService = citaService;
    }

    @PostMapping
    public ResponseEntity<Cita> registrarCita(@RequestBody Cita cita) {
        return new ResponseEntity<>(citaService.registrarCita(cita), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cita>> listarCitas() {
        return ResponseEntity.ok(citaService.listarCitas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> buscarPorId(@PathVariable Long id) {
        return citaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/especialidad/{esp}")
    public ResponseEntity<List<Cita>> listarPorEspecialidad(@PathVariable String esp) {
        return ResponseEntity.ok(citaService.listarPorEspecialidad(esp));
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<Cita>> listarPorFecha(
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fecha) {
        return ResponseEntity.ok(citaService.listarPorFecha(fecha));
    }

}
