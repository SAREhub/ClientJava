package com.sarehub.client.amqp;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rabbitmq.client.Channel;

public class AmqpProcessPromiseFactoryTest {

	@Mock
	private Channel channelMock;

	private AmqpProcessPromiseFactory factory;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		factory = new AmqpProcessPromiseFactory(channelMock);
	}

	@Test
	public void testExecuteProcessed() throws IOException {
		CompletableFuture<Void> promise = factory.create(1L);
		promise.complete(null);
		verify(channelMock, times(1)).basicAck(1L, false);
	}

	@Test
	public void testExecuteProcessedExceptionally() throws IOException {
		CompletableFuture<Void> promise = factory.create(1L);
		promise.completeExceptionally(new Exception());
		verify(channelMock, times(1)).basicReject(1L, false);
	}

	@Test
	public void testExecuteCancelled() throws IOException {
		CompletableFuture<Void> promise = factory.create(1L);
		promise.cancel(true);
		verify(channelMock, times(1)).basicReject(1L, true);
	}

}
