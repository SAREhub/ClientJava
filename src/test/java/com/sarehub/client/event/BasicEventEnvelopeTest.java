package com.sarehub.client.event;

import static org.junit.Assert.assertFalse;
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
	public void testProcessedSuccessfull() {
		envelope.markAsProcessedSuccessfull();
		Mockito.verify(processPromise, Mockito.atLeastOnce()).complete(Mockito.same(envelope));
		Mockito.when(processPromise.isDone()).thenReturn(true);
		Mockito.when(processPromise.isCancelled()).thenReturn(false);
		Mockito.when(processPromise.isCompletedExceptionally()).thenReturn(false);
		assertTrue(envelope.isProcessedSuccessfull());
	}

	@Test
	public void testCancelled() {
		envelope.markAsCancelled();
		Mockito.verify(processPromise, Mockito.atLeastOnce()).cancel(false);
		Mockito.when(processPromise.isDone()).thenReturn(true);
		Mockito.when(processPromise.isCancelled()).thenReturn(true);
		Mockito.when(processPromise.isCompletedExceptionally()).thenReturn(false);
		assertTrue(envelope.isCancelled());
		assertFalse(envelope.isProcessedSuccessfull());
	}

	@Test
	public void testProcessedExceptionally() {
		Exception e = new Exception();
		envelope.markAsProcessedExceptionally(e);
		Mockito.verify(processPromise, Mockito.atLeastOnce()).completeExceptionally(Mockito.same(e));
		Mockito.when(processPromise.isDone()).thenReturn(true);
		Mockito.when(processPromise.isCancelled()).thenReturn(false);
		Mockito.when(processPromise.isCompletedExceptionally()).thenReturn(true);
		assertTrue(envelope.isProcessedExceptionally());
		assertFalse(envelope.isProcessedSuccessfull());
	}

}
