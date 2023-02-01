package com.example.labs.services.rabbitMq;

import com.example.labs.model.MessageModel;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Binding binding;

    public void send(MessageModel body) {
        String value = """
                {
                "name": "maria madalena"
                }
                """;

        rabbitTemplate.convertAndSend(this.binding.getExchange(), this.binding.getRoutingKey(), value);
    }
}
