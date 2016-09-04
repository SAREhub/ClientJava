package com.sarehub.client.event;

public interface EventSerializer<ED> {

	public ED serialize(Event event);

}
