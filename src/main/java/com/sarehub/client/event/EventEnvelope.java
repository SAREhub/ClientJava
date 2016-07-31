package com.sarehub.client.event;

/**
 * Event wrapper for store extra information about event
 * 
 * @param <E>
 *            Event type
 */
public interface EventEnvelope<E extends Event> {

	E getEvent();

	/**
	 * Marks envelope as processed, it can call some callback for notify about that.
	 */
	void markAsProcessed();

	boolean isProcessed();

	/**
	 * Marks envelope as process cancelled, it can call some callback for notify about that.
	 */
	void markAsCancelled();

	boolean isCancelled();

	/**
	 * Marks envelope as processed with some error, it can call some callback for notify about that.
	 */
	void markAsProcessedExceptionally(Throwable e);

	boolean isProcessedExceptionally();

	EventEnvelopeProperties getProperties();

	boolean hasProperties();

}