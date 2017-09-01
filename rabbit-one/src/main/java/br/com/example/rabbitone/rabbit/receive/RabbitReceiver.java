package br.com.example.rabbitone.rabbit.receive;

import br.com.example.rabbitone.domain.Foo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

public class RabbitReceiver implements ChannelAwareMessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitReceiver.class);

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        LOGGER.info("Sucess in receive the message on RabbitOne [{}]", message.getBody());

        Foo receivedFoo = null;

        if (message.getBody() != null && message.getBody().length > 0) {
            ObjectMapper objectMapper = new ObjectMapper();

            receivedFoo = objectMapper.readValue(message.getBody(), Foo.class);
        }

        if (receivedFoo != null)
            LOGGER.info("The description of Received Foo is = " + receivedFoo.getDescription());
        else
            LOGGER.info("The object received is NULL!");
    }
}
