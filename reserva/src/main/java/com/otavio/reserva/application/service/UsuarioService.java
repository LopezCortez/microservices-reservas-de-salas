package com.otavio.reserva.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otavio.reserva.domain.model.Usuario;
import com.otavio.reserva.infrastructure.repository.UsuarioRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioService {
    private final UsuarioRepository userDataRepository; // Nomenclatura que usei na refatoração

    @Transactional
    public void processUsuarioUpdate(Usuario userInput) { // Método que está sendo chamado pelo consumer
        if (userInput == null || userInput.getId() == null) {
            log.warn("Attempted to save or update a null Usuario or Usuario with null ID. Operation skipped.");
            return;
        }

        Usuario userToSave = userDataRepository.findById(userInput.getId())
                .map(existingUser -> {
                    existingUser.setNome(userInput.getNome());
                    return existingUser;
                })
                .orElseGet(() -> {
                    Usuario newUser = new Usuario();
                    newUser.setId(userInput.getId());
                    newUser.setNome(userInput.getNome()); // Adicionei a atribuição do nome aqui para consistência
                    log.info("Creating new Usuario with ID: {}", userInput.getId());
                    return newUser;
                });

        userDataRepository.save(userToSave);
        log.info("Usuario saved/updated successfully: {}", userToSave.getId());
    }
}