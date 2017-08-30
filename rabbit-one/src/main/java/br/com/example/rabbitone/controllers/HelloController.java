package br.com.example.rabbitone.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController(value = "/hello-rabbit")
public class HelloController {

    private final static String exchangeName = "spring.boot.exchange.one";
    private final static String externalQueueName = "spring.boot.two";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    private final AtomicInteger counter = new AtomicInteger();

    @GetMapping
    public void helloRabbit() {
        LOGGER.info("Sending a message to RabbitMQ NOW! Connected on vHost [{}]", rabbitTemplate.getConnectionFactory().getVirtualHost());

        rabbitTemplate.convertAndSend(exchangeName, externalQueueName,
                "Hello from " + HelloController.class.getName() +". This is the number: " + counter.incrementAndGet() + " you call.");
    }
}
