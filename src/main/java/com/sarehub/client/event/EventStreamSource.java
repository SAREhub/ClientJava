package com.sarehub.client.event;

/**
 * Base class for all event stream sources
 */
public interface EventStreamSource<E extends Event> {

	public abstract void flow();

	public void pipe(EventStreamSink<E> sink);

	public void unpipe();
}
