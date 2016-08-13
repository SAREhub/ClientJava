package com.sarehub.client.amqp;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class AmqpHelper {
	public static Connection createConnection(AmqpConnectionConfig config) throws IOException, TimeoutException {
		config.validate();
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(config.host);
		factory.setPort(config.port);
		factory.setVirtualHost(config.vhost);
		factory.setUsername(config.username);
		factory.setPassword(config.password);
		return factory.newConnection();
	}
}
