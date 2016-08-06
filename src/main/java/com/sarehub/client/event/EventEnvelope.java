package com.sarehub.client.event;

/**
 * Event wrapper for store extra information about event
 * 
 * @param <E>
 *            Event type
 */
public interface EventEnvelope<E extends Event> {

	/**
	 * Returns event wrapped with envelope
	 */
	E getEvent();

	/**
	 * Marks envelope as processed, it can call some callback for notify about that.
	 */
	void markAsProcessedSuccessfull();

	boolean isProcessedSuccessfull();

	/**
	 * Marks envelope as process cancelled, it can call some callback for notify about that.
	 */
	void markAsCancelled();

	/**
	 * Returns true if envelope process was cancelled.
	 */
	boolean isCancelled();

	/**
	 * Marks envelope as processed with some error, it can call some callback for notify about that.
	 */
	void markAsProcessedExceptionally(Throwable e);

	/**
	 * Returns true if envelope processing generated any exception.
	 */
	boolean isProcessedExceptionally();

	/**
	 * Returns true if event was processed in any way(successful cancelled, exceptionally).
	 */
	boolean isProcessed();

	/**
	 * Returns additional properties assigned to envelope
	 */
	EventEnvelopeProperties getProperties();

	/**
	 * Returns true if properties was sets for envelope
	 */
	boolean hasProperties();

}