package br.com.example.rabbitone.controllers;

import br.com.example.rabbitone.domain.Foo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/hello-rabbit")
public class HelloController {

    private final static String exchangeName = "spring.boot.exchange.one";
    private final static String externalQueueName = "spring.boot.two";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @GetMapping
    public void helloRabbit() {
        LOGGER.info("Sending a message to RabbitMQ NOW! Connected on vHost [{}]", rabbitTemplate.getConnectionFactory().getVirtualHost());

        ObjectMapper objectMapper = new ObjectMapper();
        byte[] bytes = null;

        Foo foo = new Foo();
        foo.setDescription("This message was been created on rabbit-one project.");

        try {
            bytes = objectMapper.writeValueAsBytes(foo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if (bytes != null)
            rabbitTemplate.convertAndSend(exchangeName, externalQueueName, bytes);
        else
            LOGGER.info("Impossible to send an object null");
    }
}
