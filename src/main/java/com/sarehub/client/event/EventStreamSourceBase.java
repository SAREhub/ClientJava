package com.sarehub.client.event;

/**
 * Event stream source base class with one sink to connect
 */
public abstract class EventStreamSourceBase implements EventStreamSource {

	protected EventStreamSink sink;

	public EventStreamSourceBase() {
		sink = new NullEventStreamSink();
	}

	public void pipe(EventStreamSink sink) {
		this.sink.onUnpipe(this);
		this.sink = sink;
		this.sink.onPipe(this);
	}

	public void unpipe() {
		this.sink.onUnpipe(this);
		this.sink = new NullEventStreamSink();
	}
}
