package com.sarehub.client.event;

public abstract class EventStreamSourceBase<E extends Event> implements EventStreamSource<E> {

	protected EventStreamSink<E> sink;

	public EventStreamSourceBase() {
		sink = new NullEventStreamSink<E>();
	}

	public abstract void flow();

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
