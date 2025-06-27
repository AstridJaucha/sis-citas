package com.utp.hexagonal.dominio.puertos.entrada;

import com.utp.hexagonal.dominio.modelo.Usuario;

public interface UsuarioEntrada {
    Usuario registrarUsuario(Usuario usuario);
}
