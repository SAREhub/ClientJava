package com.sarehub.client.event;

import java.util.Queue;

public class CollectionEventStreamSource extends EventStreamSourceBase {

	private Queue<EventEnvelope> stream;

	public CollectionEventStreamSource(Queue<EventEnvelope> stream) {
		this.stream = stream;

	}

	@Override
	public void flow() {
		for (EventEnvelope eventEnvelope : stream) {
			sink.write(eventEnvelope);
		}
	}

}
