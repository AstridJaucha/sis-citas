package com.utp.hexagonal.dominio.puertos.salida;

import com.utp.hexagonal.dominio.modelo.Doctor;

import java.util.List;

public interface DoctorSalida {
    Doctor guardar(Doctor doctor);
    List<Doctor> listar();
    List<Doctor> listarPorEspecialidad(String especialidad);

}
