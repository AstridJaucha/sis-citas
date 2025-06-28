package com.utp.hexagonal.infraestructura.controller;

import com.utp.hexagonal.dominio.modelo.Cita;
import com.utp.hexagonal.dominio.puertos.entrada.CitaEntrada;
import com.utp.hexagonal.dominio.puertos.salida.PacienteSalida;
import com.utp.hexagonal.infraestructura.dto.CitaDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaEntrada citaEntrada;
    private final PacienteSalida pacienteSalida;

    public CitaController(CitaEntrada citaEntrada, PacienteSalida pacienteSalida) {
        this.citaEntrada = citaEntrada;
        this.pacienteSalida = pacienteSalida;
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
    public ResponseEntity<List<CitaDTO>> listarCitas() {
        List<Cita> citas = citaEntrada.listarCitas();

        List<CitaDTO> citasDTO = citas.stream().map(cita -> {
            CitaDTO dto = new CitaDTO();
            dto.setId(cita.getId());
            dto.setEspecialidad(cita.getEspecialidad());
            dto.setMedico(cita.getMedico());
            dto.setFechaCita(cita.getFechaCita());
            dto.setHoraCita(cita.getHoraCita());

            // Aquí obtenemos el DNI a partir del ID del paciente
            String dni = pacienteSalida.buscarPorId(cita.getPacienteId())
                    .map(p -> p.getDni())
                    .orElse("Desconocido");

            dto.setDniPaciente(dni);
            return dto;
        }).toList();

        return ResponseEntity.ok(citasDTO);
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
    @GetMapping("/detallado")
    public ResponseEntity<List<CitaDTO>> listarDetallado() {
        List<CitaDTO> citas = citaEntrada.listarCitas().stream().map(cita -> {
            CitaDTO dto = new CitaDTO();
            dto.setId(cita.getId());
            dto.setEspecialidad(cita.getEspecialidad());
            dto.setMedico(cita.getMedico());
            dto.setFechaCita(cita.getFechaCita());
            dto.setHoraCita(cita.getHoraCita());

            // Obtener el DNI del paciente
            pacienteSalida.buscarPorId(cita.getPacienteId()).ifPresent(p -> dto.setDniPaciente(p.getDni()));
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(citas);
    }
}
