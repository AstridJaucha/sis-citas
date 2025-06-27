// aplicacion/casodeuso/AuthServiceImpl.java
package com.utp.hexagonal.aplicacion.casodeuso;

import com.utp.hexagonal.dominio.modelo.Usuario;
import com.utp.hexagonal.dominio.puertos.entrada.AuthEntrada;
import com.utp.hexagonal.dominio.puertos.salida.UsuarioSalida;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthEntrada {

    private final UsuarioSalida usuarioSalida;

    public AuthServiceImpl(UsuarioSalida usuarioSalida) {
        this.usuarioSalida = usuarioSalida;
    }

    @Override
    public Usuario login(String username, String password) {
        return usuarioSalida.buscarPorUsername(username)
                .filter(u -> u.getPassword().equals(password)) // sin encriptar por simplicidad
                .orElseThrow(() -> new IllegalArgumentException("Credenciales incorrectas"));
    }
}
