package com.otavio.usuario.infrastructure.controller;

import com.otavio.usuario.application.service.UsuarioService;
import com.otavio.usuario.domain.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService userService;

    public UsuarioController(UsuarioService usuarioService) {
        this.userService = usuarioService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = userService.listar();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/new")
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
        Usuario novoUsuario = userService.salvar(usuario);
        return ResponseEntity.status(201).body(novoUsuario);
    }
}