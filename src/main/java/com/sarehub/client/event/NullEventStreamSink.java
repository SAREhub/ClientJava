package com.sarehub.client.event;

/**
 * That implementation of EventStreamSink is used when sink for EventStreamSource isn't defined
 */
public class NullEventStreamSink<E extends Event> implements EventStreamSink<E> {

	@Override
	public void write(EventEnvelope<E> eventEnvelope) {

	}

	@Override
	public void onPipe(EventStreamSource<E> source) {

	}

	@Override
	public void onUnpipe(EventStreamSource<E> source) {

	}

}
