package com.utp.hexagonal.aplicacion.casodeuso;

import com.utp.hexagonal.dominio.modelo.Especialidad;
import com.utp.hexagonal.dominio.puertos.entrada.EspecialidadEntrada;
import com.utp.hexagonal.dominio.puertos.salida.EspecialidadSalida;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadServiceImpl implements EspecialidadEntrada {

    private final EspecialidadSalida especialidadSalida;

    public EspecialidadServiceImpl(EspecialidadSalida especialidadSalida) {
        this.especialidadSalida = especialidadSalida;
    }

    @Override
    public List<Especialidad> obtenerEspecialidades() {
        return especialidadSalida.listarTodas();
    }
}
