// infraestructura/controller/AuthController.java
package com.utp.hexagonal.infraestructura.controller;

import com.utp.hexagonal.dominio.modelo.Usuario;
import com.utp.hexagonal.dominio.puertos.entrada.AuthEntrada;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthEntrada authEntrada;

    public AuthController(AuthEntrada authEntrada) {
        this.authEntrada = authEntrada;
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) {
        Usuario logueado = authEntrada.login(usuario.getUsername(), usuario.getPassword());
        return ResponseEntity.ok(logueado);
    }
}
