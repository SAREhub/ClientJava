package com.sarehub.client.event;

/**
 * Event stream sink is a place where event can come from stream
 * 
 * @param <E>
 *            Event Type
 */
public abstract class EventStreamSink<E extends Event> extends EventStream<E> {

	public abstract void write(EventEnvelope<E> eventEnvelope);

	public void onPipe(EventStreamSource<E> source) {

	}

	public void onUnpipe(EventStreamSource<E> source) {

	}

}
