package com.sarehub.client.amqp;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import com.sarehub.client.event.EventEnvelope;

public class BasicAmqpEventStreamSourceTest {

	@Mock
	private Channel channelMock;

	@Mock
	private QueueingConsumer consumerMock;

	@Mock
	private AmqpEventEnvelopeFactory eventEnvelopeFactoryMock;

	@Mock
	private EventEnvelope eventEnvelopeMock;

	private String queueName = "queue";
	private String consumerTag = "tag";

	private BasicAmqpEventStreamSource source;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(channelMock.basicConsume(anyString(), anyBoolean(), any(QueueingConsumer.class))).thenReturn(consumerTag);
		when(consumerMock.getChannel()).thenReturn(channelMock);

		source = BasicAmqpEventStreamSource.getFor(queueName).amqpConsumer(consumerMock)
				.eventEnvelopeFactory(eventEnvelopeFactoryMock);
	}

	@Test
	public void testFlowFirstUse() throws Exception {
		source.flow();
		assertTrue(source.isInFlowMode());
		verify(channelMock).basicQos(2);
		verify(channelMock).basicConsume(eq(queueName), eq(false), same(consumerMock));
	}

	@Test
	public void testFlowSecondUse() throws Exception {
		source.flow();
		source.flow();
		verify(channelMock, atLeastOnce()).basicQos(2);
		verify(channelMock, atLeastOnce()).basicConsume(eq(queueName), eq(false), same(consumerMock));
	}

	@Test
	public void testStopFlow() throws Exception {
		source.flow();
		source.stopFlow();
		verify(channelMock).basicCancel(eq(consumerTag));
		assertFalse(source.isInFlowMode());
	}

}
