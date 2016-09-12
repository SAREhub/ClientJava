package com.sarehub.client.amqp;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class AmqpHelper {

	public Connection createConnection(AmqpConnectionConfig config, ConnectionFactory factory)
			throws IOException, TimeoutException {
		config.validate();
		factory.setHost(config.getHost());
		factory.setPort(config.getPort());
		factory.setVirtualHost(config.getVhost());
		factory.setUsername(config.getUsername());
		factory.setPassword(config.getPassword());
		return factory.newConnection();
	}
}
