package com.sarehub.client.event;

import static org.mockito.ArgumentMatchers.any;
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
	private EventEnvelope eventEnvelopeMock1;

	@Mock
	private EventEnvelope eventEnvelopeMock2;

	@Mock
	private NullEventStreamSink sinkMock;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFlow() {
		Queue<EventEnvelope> q = new ArrayBlockingQueue<EventEnvelope>(5);
		q.add(this.eventEnvelopeMock1);
		q.add(this.eventEnvelopeMock2);
		CollectionEventStreamSource source = new CollectionEventStreamSource(q);

		source.pipe(sinkMock);
		source.flow();
		verify(sinkMock, times(2)).write(any(EventEnvelope.class));
	}

}
