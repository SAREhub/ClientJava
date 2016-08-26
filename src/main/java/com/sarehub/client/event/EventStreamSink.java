package com.sarehub.client.event;

/**
 * Event stream sink is a place where event can come from stream
 * 
 * @param <E>
 *            Event Type
 */
public interface EventStreamSink {

	public abstract void write(EventEnvelope eventEnvelope);

	public void onPipe(EventStreamSource source);

	public void onUnpipe(EventStreamSource source);

}
