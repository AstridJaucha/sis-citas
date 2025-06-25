package com.utp.hexagonal.infraestructura.config;

import com.utp.hexagonal.aplicacion.casodeuso.PacienteServiceImpl;
import com.utp.hexagonal.dominio.puertos.entrada.PacienteEntrada;
import com.utp.hexagonal.dominio.puertos.salida.EspecialidadSalida;
import com.utp.hexagonal.dominio.puertos.salida.PacienteSalida;
import com.utp.hexagonal.infraestructura.client.ReniecClient;
import com.utp.hexagonal.infraestructura.repository.EspecialidadJPARepository;
import com.utp.hexagonal.infraestructura.repository.EspecialidadJPARepositoryAdapter;
import com.utp.hexagonal.infraestructura.repository.PacienteJPARepository;
import com.utp.hexagonal.infraestructura.repository.PacienteJPARepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AplicationConfig {

    @Bean
    public PacienteSalida pacienteSalida(PacienteJPARepository repo) {
        return new PacienteJPARepositoryAdapter(repo);
    }

    @Bean
    public PacienteEntrada pacienteEntrada(PacienteSalida salida, ReniecClient reniecClient) {
        return new PacienteServiceImpl(salida, reniecClient);
    }

    @Bean
    public EspecialidadSalida especialidadSalida(EspecialidadJPARepository repo) {
        return new EspecialidadJPARepositoryAdapter(repo);
    }

}
