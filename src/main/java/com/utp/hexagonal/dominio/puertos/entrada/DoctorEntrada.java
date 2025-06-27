package com.utp.hexagonal.dominio.puertos.entrada;

import com.utp.hexagonal.dominio.modelo.Doctor;
import java.util.List;

public interface DoctorEntrada {
    List<Doctor> listarDoctores();
    List<Doctor> listarPorEspecialidad(String especialidad);

}
