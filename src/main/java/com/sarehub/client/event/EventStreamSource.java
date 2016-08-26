package com.sarehub.client.event;

/**
 * Base class for all event stream sources
 */
public interface EventStreamSource {

	public abstract void flow() throws Exception;

	public void pipe(EventStreamSink sink);

	public void unpipe();
}
