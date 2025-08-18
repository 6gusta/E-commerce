package com.ecomerccer.loja.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class PedidoRabbitProducer {

    private final RabbitTemplate rabbitTemplate;

    public PedidoRabbitProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviamsg(String fila, Object mensagem) {
        rabbitTemplate.convertAndSend(fila, mensagem);
    }
}
