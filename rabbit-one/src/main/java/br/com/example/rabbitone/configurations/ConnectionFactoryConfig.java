package br.com.example.rabbitone.configurations;

import com.rabbitmq.client.ConnectionFactory;

public class ConnectionFactoryConfig {

    private String host;
    private String username;
    private String password;
    private String virtualHost;
    private Integer port;

    public ConnectionFactoryConfig(String host, String username, String password, String virtualHost, Integer port) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.virtualHost = virtualHost;
        this.port = port;
    }

    public ConnectionFactory config() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(this.host);
        connectionFactory.setPort(this.port);
        connectionFactory.setUsername(this.username);
        connectionFactory.setPassword(this.password);
        connectionFactory.setVirtualHost(this.virtualHost);

        return connectionFactory;
    }

}
