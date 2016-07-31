package com.sarehub.client.event;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.CompletableFuture;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class BasicEventEnvelopeTest {

	@Mock
	private Event eventMock;

	@Mock
	private CompletableFuture<EventEnvelope<Event>> processPromise;

	private EventEnvelope<Event> envelope;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		envelope = new BasicEventEnvelope<Event>(eventMock, processPromise);
	}

	@Test
	public void testProcessed() {
		envelope.markAsProcessed();
		Mockito.verify(processPromise, Mockito.atLeastOnce()).complete(Mockito.same(envelope));
		Mockito.when(processPromise.isDone()).thenReturn(true);
		assertTrue(envelope.isProcessed());
	}

	@Test
	public void testCancelled() {
		envelope.markAsCancelled();
		Mockito.verify(processPromise, Mockito.atLeastOnce()).cancel(false);
		Mockito.when(processPromise.isCancelled()).thenReturn(true);
		assertTrue(envelope.isCancelled());
	}

}
