package com.otavio.sala.application.service;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otavio.sala.domain.model.Sala;
import com.otavio.sala.infrastructure.repository.SalaRepository;

import java.util.List;

@Service
public class SalaService {
    private final SalaRepository roomRepository;
    private final RabbitTemplate messageSender;
    private final String roomExchangeName;

    public SalaService(
            SalaRepository salaRepository,
            RabbitTemplate rabbitTemplate,
            @Value("${sala.rabbitmq.exchange}") String salaExchangeName
    ) {
        this.roomRepository = salaRepository;
        this.messageSender = rabbitTemplate;
        this.roomExchangeName = salaExchangeName;
    }

    public List<Sala> listar() {
        return roomRepository.findAll();
    }

    @Transactional
    public Sala salvar(Sala sala) {
        Sala createdRoom = roomRepository.save(sala);
        System.out.println("Sala criada com ID: " + createdRoom.getId());

        try {
            messageSender.convertAndSend(roomExchangeName, "", createdRoom);
            System.out.println("Evento de Sala publicado para o exchange, ID da Sala: " + createdRoom.getId());
        } catch (AmqpException e) {
            System.err.println("Erro ao publicar evento de Sala: " + e.getMessage());
        }

        return createdRoom;
    }
}