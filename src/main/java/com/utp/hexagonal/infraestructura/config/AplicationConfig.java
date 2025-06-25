package com.utp.hexagonal.infraestructura.config;

import com.utp.hexagonal.aplicacion.casodeuso.PacienteServiceImpl;
import com.utp.hexagonal.dominio.puertos.entrada.PacienteEntrada;
import com.utp.hexagonal.dominio.puertos.salida.PacienteSalida;
import com.utp.hexagonal.infraestructura.repository.PacienteJPARepository;
import com.utp.hexagonal.infraestructura.repository.PacienteJPARepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AplicationConfig {

    @Bean
    public PacienteEntrada pacienteEntrada(PacienteSalida pacienteSalida) {
        return new PacienteServiceImpl(pacienteSalida);
    }

    @Bean
    public PacienteSalida pacienteSalida(PacienteJPARepository pacienteJPARepository) {
        return new PacienteJPARepositoryAdapter(pacienteJPARepository);
    }
}




