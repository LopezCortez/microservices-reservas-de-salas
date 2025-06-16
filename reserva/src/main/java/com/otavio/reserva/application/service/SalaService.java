package com.otavio.reserva.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otavio.reserva.domain.model.Sala;
import com.otavio.reserva.infrastructure.repository.SalaRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SalaService {
    private final SalaRepository roomDataRepository;

    @Transactional
    public void processSalaUpdate(Sala salaInput) {
        if (salaInput == null || salaInput.getId() == null) {
            log.warn("Attempted to save or update a null Sala or Sala with null ID. Operation skipped.");
            return;
        }

        Optional<Sala> existingRoomOptional = roomDataRepository.findById(salaInput.getId());

        Sala roomToPersist;
        if (existingRoomOptional.isPresent()) {
            roomToPersist = existingRoomOptional.get();
            roomToPersist.setNome(salaInput.getNome());
        } else {
            roomToPersist = new Sala();
            roomToPersist.setId(salaInput.getId());
            roomToPersist.setNome(salaInput.getNome());
            log.info("Creating new Sala with ID: {}", salaInput.getId());
        }

        roomDataRepository.save(roomToPersist);
        log.info("Sala saved/updated successfully: {}", roomToPersist.getId());
    }
}