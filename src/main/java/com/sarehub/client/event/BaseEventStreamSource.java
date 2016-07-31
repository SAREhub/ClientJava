package com.sarehub.client.event;

public abstract class BaseEventStreamSource<E extends Event> implements EventStreamSource<E> {

	protected EventStreamSink<E> sink;

	public BaseEventStreamSource() {
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
