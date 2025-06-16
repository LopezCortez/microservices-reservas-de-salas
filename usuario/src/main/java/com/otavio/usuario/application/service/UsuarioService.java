package com.otavio.usuario.application.service;

import com.otavio.usuario.domain.model.Usuario;
import com.otavio.usuario.infrastructure.repository.UsuarioRepository;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository userRepository;
    private final RabbitTemplate messagingTemplate;
    private final String userEventsExchange;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            RabbitTemplate rabbitTemplate,
            @Value("${usuario.rabbitmq.exchange}") String usuarioExchangeName
    ) {
        this.userRepository = usuarioRepository;
        this.messagingTemplate = rabbitTemplate;
        this.userEventsExchange = usuarioExchangeName;
    }

    public List<Usuario> listar() {
        return userRepository.findAll();
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        Usuario savedUser = userRepository.save(usuario);
        System.out.println("Usuário salvo com sucesso, ID: " + savedUser.getId());

        try {
            messagingTemplate.convertAndSend(userEventsExchange, "", savedUser);
            System.out.println("Evento de Usuário publicado para o exchange, ID do Usuário: " + savedUser.getId());
        } catch (AmqpException e) {
            System.err.println("Falha ao publicar evento de Usuário. Erro: " + e.getMessage());
        }

        return savedUser;
    }
}