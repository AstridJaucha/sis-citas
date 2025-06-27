// DoctorServiceImpl.java
package com.utp.hexagonal.aplicacion.casodeuso;

import com.utp.hexagonal.dominio.modelo.Doctor;
import com.utp.hexagonal.dominio.puertos.entrada.DoctorEntrada;
import com.utp.hexagonal.dominio.puertos.salida.DoctorSalida;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorEntrada {

    private final DoctorSalida doctorSalida;

    public DoctorServiceImpl(DoctorSalida doctorSalida) {
        this.doctorSalida = doctorSalida;
    }

    @Override
    public List<Doctor> listarDoctores() {
        return doctorSalida.listar();
    }

    @Override
    public List<Doctor> listarPorEspecialidad(String especialidad) {
        return doctorSalida.listarPorEspecialidad(especialidad);
    }

}
