package com.utp.hexagonal.dominio.puertos.salida;

import com.utp.hexagonal.dominio.modelo.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioSalida {
    Optional<Usuario> buscarPorUsername(String username);
    Usuario guardar(Usuario usuario);
    List<Usuario> listarTodos();
}
