package com.otavio.sala.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otavio.sala.domain.model.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long> {}
