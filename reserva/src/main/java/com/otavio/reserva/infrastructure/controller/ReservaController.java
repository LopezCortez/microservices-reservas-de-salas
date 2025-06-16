package com.otavio.reserva.infrastructure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.otavio.reserva.application.service.ReservaService;
import com.otavio.reserva.domain.model.Reserva;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class ReservaController {
    private final ReservaService bookingManagementService;

    public ReservaController(ReservaService reservaService) {
        this.bookingManagementService = reservaService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Reserva>> retrieveAllReservas() {
        List<Reserva> reservas = bookingManagementService.getAllReservas();
        return ResponseEntity.ok(reservas);
    }

    @PostMapping("/create")
    public ResponseEntity<Reserva> createNewReserva(@RequestBody Reserva reserva) {
        Reserva savedReserva = bookingManagementService.registerReserva(reserva);
        return new ResponseEntity<>(savedReserva, HttpStatus.CREATED);
    }
}