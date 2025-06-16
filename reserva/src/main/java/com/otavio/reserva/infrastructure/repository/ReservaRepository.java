package com.otavio.reserva.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otavio.reserva.domain.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {}
