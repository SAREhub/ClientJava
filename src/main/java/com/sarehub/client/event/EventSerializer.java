package com.sarehub.client.event;

public interface EventSerializer<R> {

	public R serialize(Event event);

}
