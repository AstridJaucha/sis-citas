package com.utp.hexagonal.infraestructura.repository;

import com.utp.hexagonal.dominio.modelo.Usuario;
import com.utp.hexagonal.dominio.puertos.salida.UsuarioSalida;
import com.utp.hexagonal.infraestructura.entity.UsuarioEntity;
import com.utp.hexagonal.infraestructura.repository.PacienteJPARepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UsuarioJPARepositoryAdapter implements UsuarioSalida {

    private final UsuarioJPARepository usuarioRepo;
    private final PacienteJPARepository pacienteRepo;

    public UsuarioJPARepositoryAdapter(UsuarioJPARepository usuarioRepo, PacienteJPARepository pacienteRepo) {
        this.usuarioRepo = usuarioRepo;
        this.pacienteRepo = pacienteRepo;
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        UsuarioEntity entity = UsuarioEntity.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .rol(usuario.getRol())
                .build();

        // Asociar paciente si viene pacienteId
        if (usuario.getPacienteId() != null) {
            pacienteRepo.findById(usuario.getPacienteId()).ifPresent(entity::setPaciente);
        }

        UsuarioEntity guardado = usuarioRepo.save(entity);

        return Usuario.builder()
                .id(guardado.getId())
                .username(guardado.getUsername())
                .password(guardado.getPassword())
                .rol(guardado.getRol())
                .pacienteId(guardado.getPaciente() != null ? guardado.getPaciente().getId() : null)
                .build();
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepo.findAll().stream().map(e ->
                Usuario.builder()
                        .id(e.getId())
                        .username(e.getUsername())
                        .password(e.getPassword())
                        .rol(e.getRol())
                        .pacienteId(e.getPaciente() != null ? e.getPaciente().getId() : null)
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepo.findByUsername(username)
                .map(e -> Usuario.builder()
                        .id(e.getId())
                        .username(e.getUsername())
                        .password(e.getPassword())
                        .rol(e.getRol())
                        .pacienteId(e.getPaciente() != null ? e.getPaciente().getId() : null)
                        .build());
    }
}
