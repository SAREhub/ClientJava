package com.sarehub.client.amqp;

public class AmqpException extends Exception {

	public AmqpException(String message) {
		super(message);
	}

	public AmqpException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = 1L;
}
