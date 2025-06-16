package com.otavio.reserva.application.service;

import org.springframework.stereotype.Service;
import com.otavio.reserva.domain.model.Reserva;
import com.otavio.reserva.infrastructure.repository.ReservaRepository;

import java.util.List;

@Service
public class ReservaService {
    private final ReservaRepository bookingRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.bookingRepository = reservaRepository;
    }

    public List<Reserva> getAllReservas() {
        return bookingRepository.findAll();
    }

    public Reserva registerReserva(Reserva reserva) {
        return bookingRepository.save(reserva);
    }
}