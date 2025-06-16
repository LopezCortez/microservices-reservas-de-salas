package com.otavio.reserva.infrastructure.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.otavio.reserva.application.service.SalaService;
import com.otavio.reserva.application.service.UsuarioService;
import com.otavio.reserva.domain.model.Sala;
import com.otavio.reserva.domain.model.Usuario;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMQConsumer {
    private final SalaService roomServiceHandler;
    private final UsuarioService userServiceHandler;

    @RabbitListener(queues = "${sala.rabbitmq.queue}")
    public void processRoomMessage(@Payload Sala incomingSala) {
        try {
            roomServiceHandler.processSalaUpdate(incomingSala);
            log.info("Mensagem de Sala processada com sucesso para ID: {}", incomingSala.getId());
        } catch (Exception e) {
            log.error("Falha ao processar mensagem de Sala para ID: {}", incomingSala.getId(), e);
            throw new AmqpRejectAndDontRequeueException("Erro ao processar mensagem de Sala", e);
        }
    }

    @RabbitListener(queues = "${usuario.rabbitmq.queue}")
    public void processUserMessage(@Payload Usuario incomingUsuario) {
        try {
            userServiceHandler.processUsuarioUpdate(incomingUsuario);
            log.info("Mensagem de Usuário processada com sucesso para ID: {}", incomingUsuario.getId());
        } catch (Exception e) {
            log.error("Falha ao processar mensagem de Usuário para ID: {}", incomingUsuario.getId(), e);
            throw new AmqpRejectAndDontRequeueException("Erro ao processar mensagem de Usuário", e);
        }
    }
}