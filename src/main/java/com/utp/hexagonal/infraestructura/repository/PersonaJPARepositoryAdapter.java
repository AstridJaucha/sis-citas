package com.utp.hexagonal.infraestructura.repository;

import com.utp.hexagonal.dominio.modelo.Persona;
import com.utp.hexagonal.dominio.puertos.salida.PersonaSalida;
import org.springframework.stereotype.Component;

import com.utp.hexagonal.infraestructura.entity.PersonaEntity;
import java.util.Optional;

@Component
public class PersonaJPARepositoryAdapter implements PersonaSalida {

    private final PersonaJPARepository personaJPARepository;

    public PersonaJPARepositoryAdapter(PersonaJPARepository personaJPARepository){
        this.personaJPARepository = personaJPARepository;
    }

    @Override
    public Persona crearPersona (Persona persona) {
        PersonaEntity personaEntity = PersonaEntity.delModeloDominio (persona);
        return personaJPARepository.save(personaEntity).alModeloDominio();
    }
    @Override
    public Optional<Persona> getPersona (Long id) {
        return personaJPARepository.findById(id).map (PersonaEntity:: alModeloDominio);
    }
    @Override
    public Optional<Persona> actualizarPersona (Long id, Persona persona) {
        if (personaJPARepository.existsById(id)){
            PersonaEntity personaEntity = PersonaEntity.delModeloDominio (persona);
            return Optional.of(personaJPARepository.save (personaEntity).alModeloDominio());
        }
        return Optional.empty();
    }
    @Override
    public boolean borrarPersona(Long id) {
        if (personaJPARepository.existsById(id)){
            personaJPARepository.deleteById(id);
            return true;
        }
        return false;
    }

}
