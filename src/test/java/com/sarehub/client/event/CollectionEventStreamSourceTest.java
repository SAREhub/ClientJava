package com.sarehub.client.event;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CollectionEventStreamSourceTest {

	@Mock
	private GenericEventEnvelope eventEnvelopeMock1;

	@Mock
	private GenericEventEnvelope eventEnvelopeMock2;

	@Mock
	private NullEventStreamSink<Event> sinkMock;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFlow() {
		Queue<EventEnvelope<Event>> q = new ArrayBlockingQueue<EventEnvelope<Event>>(5);
		q.add(this.eventEnvelopeMock1);
		q.add(this.eventEnvelopeMock2);
		CollectionEventStreamSource<Event> source = new CollectionEventStreamSource<Event>(q);

		source.pipe(sinkMock);
		source.flow();
		verify(sinkMock, times(2)).write(any(GenericEventEnvelope.class));
	}

}
