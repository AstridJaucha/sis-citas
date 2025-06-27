package com.utp.hexagonal.aplicacion.casodeuso;

import com.utp.hexagonal.dominio.modelo.Usuario;
import com.utp.hexagonal.dominio.puertos.entrada.UsuarioEntrada;
import com.utp.hexagonal.dominio.puertos.salida.UsuarioSalida;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioEntrada {

    private final UsuarioSalida usuarioSalida;

    public UsuarioServiceImpl(UsuarioSalida usuarioSalida) {
        this.usuarioSalida = usuarioSalida;
    }

    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        return usuarioSalida.guardar(usuario);
    }
}
