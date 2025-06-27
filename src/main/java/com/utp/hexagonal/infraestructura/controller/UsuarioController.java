package com.utp.hexagonal.infraestructura.controller;

import com.utp.hexagonal.dominio.modelo.Usuario;
import com.utp.hexagonal.dominio.puertos.entrada.UsuarioEntrada;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    private final UsuarioEntrada usuarioEntrada;

    public UsuarioController(UsuarioEntrada usuarioEntrada) {
        this.usuarioEntrada = usuarioEntrada;
    }

    @PostMapping
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario) {
        Usuario nuevo = usuarioEntrada.registrarUsuario(usuario);
        return ResponseEntity.ok(nuevo);
    }
}
