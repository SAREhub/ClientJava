package com.sarehub.client.event;

import java.util.Queue;

public class CollectionEventStreamSource<E extends Event> extends BaseEventStreamSource<E> {

	private Queue<EventEnvelope<E>> stream;

	public CollectionEventStreamSource(Queue<EventEnvelope<E>> stream) {
		this.stream = stream;

	}

	@Override
	public void flow() {
		for (EventEnvelope<E> eventEnvelope : stream) {
			sink.write(eventEnvelope);
		}
	}

	@Override
	public EventEnvelope<E> read() {
		EventEnvelope<E> eventEnvelope = stream.poll();
		if (eventEnvelope != null) {
			sink.write(eventEnvelope);
		}

		return eventEnvelope;
	}

}
