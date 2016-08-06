package com.sarehub.client.event;

public class EventDeserializeException extends Exception {

	public EventDeserializeException(String message) {
		super(message);
	}

	public EventDeserializeException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = -1200244751123385080L;

}
