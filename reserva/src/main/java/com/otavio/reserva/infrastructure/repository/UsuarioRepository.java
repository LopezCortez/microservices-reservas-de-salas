package com.otavio.reserva.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otavio.reserva.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
