// infraestructura/repository/UsuarioJPARepositoryAdapter.java
package com.utp.hexagonal.infraestructura.repository;

import com.utp.hexagonal.dominio.modelo.Usuario;
import com.utp.hexagonal.dominio.puertos.salida.UsuarioSalida;
import com.utp.hexagonal.infraestructura.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UsuarioJPARepositoryAdapter implements UsuarioSalida {

    private final UsuarioJPARepository usuarioRepo;

    public UsuarioJPARepositoryAdapter(UsuarioJPARepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        UsuarioEntity entity = UsuarioEntity.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .rol(usuario.getRol())
                .build();
        UsuarioEntity guardado = usuarioRepo.save(entity);
        return Usuario.builder()
                .id(guardado.getId())
                .username(guardado.getUsername())
                .password(guardado.getPassword())
                .rol(guardado.getRol())
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
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepo.findByUsername(username)
                .map(entity -> Usuario.builder()
                        .id(entity.getId())
                        .username(entity.getUsername())
                        .password(entity.getPassword())
                        .rol(entity.getRol())
                        .build());
    }
}
