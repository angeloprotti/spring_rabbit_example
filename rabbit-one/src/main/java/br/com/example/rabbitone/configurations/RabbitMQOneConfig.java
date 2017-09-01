package br.com.example.rabbitone.configurations;

import br.com.example.rabbitone.rabbit.receive.RabbitReceiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitMQOneConfig {

    private final static String queueName = "spring.boot.one";
    private final static String externalExchangeName = "spring.boot.exchange.two";

    @Bean
    @Primary
    ConnectionFactory connectionFactory() {
        ConnectionFactoryConfig connectionFactoryConfig =
                new ConnectionFactoryConfig("localhost", "guest", "guest", "local2", 5672);
        return new CachingConnectionFactory(connectionFactoryConfig.config());
    }

    @Bean
    ConnectionFactory secondaryConnectionFactory() {
        ConnectionFactoryConfig connectionFactoryConfig =
                new ConnectionFactoryConfig("localhost", "guest", "guest", "local", 5672);
        return new CachingConnectionFactory(connectionFactoryConfig.config());
    }

    @Bean
    RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(secondaryConnectionFactory());
    }

    @Bean
    Queue queue() {
        return new Queue(queueName);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(externalExchangeName);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }

    @Bean
    MessageListenerAdapter listenerAdapter() {
        return new MessageListenerAdapter(new RabbitReceiver());
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);

        return container;
    }

}
