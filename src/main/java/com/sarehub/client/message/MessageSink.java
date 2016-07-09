package com.sarehub.client.message;


public interface MessageSink<T extends MessageEnvelope> {
	
	public void write(T messageEnvelope) throws Exception;
}
