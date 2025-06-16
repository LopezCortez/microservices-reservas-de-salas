package com.otavio.sala.infrastructure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.otavio.sala.application.service.SalaService;
import com.otavio.sala.domain.model.Sala;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {
    private final SalaService roomService;

    public SalaController(SalaService salaService) {
        this.roomService = salaService;
    }

    @GetMapping("/available")
    public ResponseEntity<List<Sala>> getAllRooms() {
        List<Sala> rooms = roomService.listar();
        return ResponseEntity.ok(rooms);
    }

    @PostMapping("/create")
    public ResponseEntity<Sala> createRoom(@RequestBody Sala sala) {
        Sala newRoom = roomService.salvar(sala);
        return new ResponseEntity<>(newRoom, HttpStatus.CREATED);
    }
}