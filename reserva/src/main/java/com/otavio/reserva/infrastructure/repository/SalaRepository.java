package com.otavio.reserva.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otavio.reserva.domain.model.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long> {}
