package com.sarehub.client.amqp;

import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.sarehub.client.event.BasicEvent;
import com.sarehub.client.event.EventDeserializationService;
import com.sarehub.client.event.EventDeserializeException;
import com.sarehub.client.event.EventEnvelope;

public class AmqpEventEnvelopeFactoryTest {

	@Mock
	private BasicEvent eventMock;

	@Mock
	private EventDeserializationService<ByteBuffer> deserializationServiceMock;

	@Mock
	private AmqpProcessPromiseFactory processPromiseFactory;

	@Mock
	private Delivery deliveryMock;

	@Mock
	private Envelope envelopeMock;

	@Mock
	private BasicProperties propertiesMock;

	@Before
	public void setUp() throws EventDeserializeException {
		MockitoAnnotations.initMocks(this);
		when(deliveryMock.getBody()).thenReturn("test".getBytes());

		when(deliveryMock.getEnvelope()).thenReturn(envelopeMock);
		when(envelopeMock.getExchange()).thenReturn("test");
		when(envelopeMock.getRoutingKey()).thenReturn("test.test");
		when(envelopeMock.getDeliveryTag()).thenReturn(1L);

		when(deliveryMock.getProperties()).thenReturn(propertiesMock);

		when(deserializationServiceMock.deserialize(any())).thenReturn(eventMock);

		when(processPromiseFactory.create(1L)).thenReturn(new CompletableFuture<>());
	}

	@Test
	public void testCreateFromDelivery() throws AmqpException, EventDeserializeException {
		AmqpEventEnvelopeFactory factory = new AmqpEventEnvelopeFactory(deserializationServiceMock, processPromiseFactory);

		EventEnvelope envelope = factory.createFromDelivery(deliveryMock);
		assertNotNull(envelope);
		assertSame(eventMock, envelope.getEvent());
		assertTrue(envelope.hasProperties());

		verify(deserializationServiceMock, times(1)).deserialize(any());
		verify(processPromiseFactory, times(1)).create(1L);
	}

}
