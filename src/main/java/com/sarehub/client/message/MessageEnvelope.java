package com.sarehub.client.message;

public class MessageEnvelope {
	
	public Message body;
	
	public MessageEnvelope(Message body) {
		this.body = body;
	}

	public Message getBody() {
		return body;
	}

}
