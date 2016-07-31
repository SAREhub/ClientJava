package com.sarehub.client.event;

import java.util.concurrent.CompletableFuture;

/**
 * EventEnvelope is wrapper for event.
 * 
 * It's use 'promise' concept based on CompletableFuture from java 8 for process state notification
 * 
 * @param <E>
 *            Event type
 */
public class BasicEventEnvelope<E extends Event> implements EventEnvelope<E> {

	private E event;

	private EventEnvelopeProperties properties;

	private CompletableFuture<EventEnvelope<E>> processPromise;

	public BasicEventEnvelope(E event, CompletableFuture<EventEnvelope<E>> processPromise) {
		this(event, processPromise, null);
	}

	public BasicEventEnvelope(E event, CompletableFuture<EventEnvelope<E>> processPromise, EventEnvelopeProperties properties) {
		this.event = event;
		this.processPromise = processPromise;
		this.properties = properties;
	}

	@Override
	public E getEvent() {
		return event;
	}

	@Override
	public void markAsProcessed() {
		processPromise.complete(this);
	}

	@Override
	public boolean isProcessed() {
		return processPromise.isDone();
	}

	@Override
	public void markAsCancelled() {
		processPromise.cancel(false);
	}

	@Override
	public boolean isCancelled() {
		return processPromise.isCancelled();
	}

	@Override
	public void markAsProcessedExceptionally(Throwable e) {
		processPromise.completeExceptionally(e);

	}

	@Override
	public boolean isProcessedExceptionally() {
		return processPromise.isCompletedExceptionally();
	}

	@Override
	public EventEnvelopeProperties getProperties() {
		return properties;
	}

	@Override
	public boolean hasProperties() {
		return properties != null;
	}

}
