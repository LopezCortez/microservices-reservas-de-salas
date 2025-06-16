package com.otavio.reserva.infrastructure.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConsumerConfig {
    @Value("${sala.rabbitmq.exchange}")
    private String salaExchangeName;
    @Value("${usuario.rabbitmq.exchange}")
    private String usuarioExchangeName;

    @Value("${sala.rabbitmq.queue}")
    private String salaQueueName;
    @Value("${usuario.rabbitmq.queue}")
    private String usuarioQueueName;

    @Bean
    public FanoutExchange salaExchange() {
        return new FanoutExchange(salaExchangeName, true, false);
    }

    @Bean
    public FanoutExchange usuarioExchange() {
        return new FanoutExchange(usuarioExchangeName, true, false);
    }

    @Bean
    public Queue salaQueue() {
        return new Queue(salaQueueName, true, false, false);
    }

    @Bean
    public Queue usuarioQueue() {
        return new Queue(usuarioQueueName, true, false, false);
    }

    @Bean
    public Binding salaBinding(Queue salaQueue, FanoutExchange salaExchange) {
        return BindingBuilder.bind(salaQueue).to(salaExchange);
    }

    @Bean
    public Binding usuarioBinding(Queue usuarioQueue, FanoutExchange usuarioExchange) {
        return BindingBuilder.bind(usuarioQueue).to(usuarioExchange);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
