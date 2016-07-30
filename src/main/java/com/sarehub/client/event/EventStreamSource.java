package com.sarehub.client.event;

/**
 * Base class for all event stream sources
 */
public abstract class EventStreamSource<E extends Event> extends EventStream<E> {

	protected EventStreamSink<E> sink;

	public EventStreamSource() {
		sink = new NullEventStreamSink<E>();
	}

	public abstract void flow();

	public abstract EventEnvelope<E> read();

	public void pipe(EventStreamSink<E> sink) {
		this.sink.onUnpipe(this);
		this.sink = sink;
		this.sink.onPipe(this);
	}

	public void unpipe() {
		this.sink.onUnpipe(this);
		this.sink = new NullEventStreamSink<E>();
	}
}
