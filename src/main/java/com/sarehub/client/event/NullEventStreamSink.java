package com.sarehub.client.event;

/**
 * That implementation of EventStreamSink is used when sink for EventStreamSource isn't defined
 */
public class NullEventStreamSink implements EventStreamSink {

	@Override
	public void write(EventEnvelope eventEnvelope) {

	}

	@Override
	public void onPipe(EventStreamSource source) {

	}

	@Override
	public void onUnpipe(EventStreamSource source) {

	}

}
