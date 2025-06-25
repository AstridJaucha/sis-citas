package com.utp.hexagonal.infraestructura.config;

import com.utp.hexagonal.aplicacion.casodeuso.PacienteServiceImpl;
import com.utp.hexagonal.dominio.puertos.entrada.PacienteEntrada;
import com.utp.hexagonal.dominio.puertos.salida.PacienteSalida;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public PacienteEntrada pacienteEntrada(PacienteSalida salida) {
        return new PacienteServiceImpl(salida);
    }
}





/*
package com.utp.hexagonal.infraestructura.config;

import com.utp.hexagonal.aplicacion.casodeuso.PersonaServiceImpl;
import com.utp.hexagonal.aplicacion.service.PersonaService;
import com.utp.hexagonal.dominio.puertos.salida.PersonaSalida;
import com.utp.hexagonal.infraestructura.repository. PersonaJPARepository;
import com.utp.hexagonal.infraestructura.repository. PersonaJPARepositoryAdapter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AplicationConfig {
    @Bean
    public PersonaService personaService (PersonaSalida personaSalida){
        return new PersonaService (new PersonaServiceImpl(personaSalida));
    }

    //@Bean
    //public PersonaEntrada personaEntrada(PersonaSalida personaSalida) {
    //  return new PersonaServiceImpl(personaSalida);
    //}


    @Bean
    public PersonaSalida personaSalida (PersonaJPARepository personaJPARepository) {
        return new PersonaJPARepositoryAdapter (personaJPARepository);
    }
}

 */