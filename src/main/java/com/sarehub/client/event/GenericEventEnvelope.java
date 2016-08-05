package com.sarehub.client.event;

import java.util.concurrent.CompletableFuture;

/**
 * That event envelope can be used in situations where event type can't be casted to real type.
 */
public class GenericEventEnvelope extends BasicEventEnvelope<Event> {

	public GenericEventEnvelope(Event event, CompletableFuture<EventEnvelope<Event>> processPromise) {
		super(event, processPromise);
	}

}
