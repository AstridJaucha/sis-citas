package com.utp.hexagonal.dominio.puertos.entrada;

import com.utp.hexagonal.dominio.modelo.Especialidad;

import java.util.List;

public interface EspecialidadEntrada {
    List<Especialidad> obtenerEspecialidades();
}
