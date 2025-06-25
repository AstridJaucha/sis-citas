package com.utp.hexagonal.dominio.puertos.salida;

import com.utp.hexagonal.dominio.modelo.Especialidad;
import java.util.List;

public interface EspecialidadSalida {
    List<Especialidad> listarTodas();
}
