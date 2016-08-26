package com.sarehub.client.event;

import java.util.concurrent.CompletableFuture;

/**
 * EventEnvelope is wrapper for event.
 * 
 * It's use 'promise' concept based on CompletableFuture from java 8 for process state notification
 */
public class BasicEventEnvelope implements EventEnvelope {

	private Event event;

	private EventEnvelopeProperties properties;

	private CompletableFuture<Void> processPromise;

	public BasicEventEnvelope(Event event) {
		this(event, null);
	}

	public BasicEventEnvelope(Event event, EventEnvelopeProperties properties) {
		this.event = event;
		this.properties = properties;
	}

	@Override
	public Event getEvent() {
		return event;
	}

	@Override
	public void markAsProcessedSuccessfull() {
		processPromise.complete(null);
	}

	@Override
	public boolean isProcessedSuccessfull() {
		return isProcessed() && !isCancelled() && !isProcessedExceptionally();
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
	public boolean isProcessed() {
		return processPromise.isDone();
	}

	public void setProcessPromise(CompletableFuture<Void> processPromise) {
		this.processPromise = processPromise;
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
