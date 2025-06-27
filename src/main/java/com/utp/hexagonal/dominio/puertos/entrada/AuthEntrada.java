// dominio/puertos/entrada/AuthEntrada.java
package com.utp.hexagonal.dominio.puertos.entrada;

import com.utp.hexagonal.dominio.modelo.Usuario;

public interface AuthEntrada {
    Usuario login(String username, String password);
}
