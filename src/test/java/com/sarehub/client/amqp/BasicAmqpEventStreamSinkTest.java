package com.sarehub.client.amqp;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.sarehub.client.event.Event;
import com.sarehub.client.event.EventEnvelope;
import com.sarehub.client.event.EventSerializationService;
import com.sarehub.client.event.EventSerializeException;

public class BasicAmqpEventStreamSinkTest {

	private BasicAmqpEventStreamSink sink;

	@Mock
	private Channel channelMock;

	@Mock
	private EventSerializationService<String> serializationServiceMock;

	@Mock
	private Event eventMock;

	@Mock
	private EventEnvelope eventEnvelopeMock;

	@Mock
	private AmqpEventEnvelopeProperties eventEnvelopePropertiesMock;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		sink = new BasicAmqpEventStreamSink(channelMock, "test", serializationServiceMock);
	}

	@Test
	public void testWrite() throws EventSerializeException, IOException {
		Mockito.when(eventEnvelopePropertiesMock.getRoutingKey()).thenReturn(new RoutingKey(new String[] { "test", "test" }));
		Mockito.when(eventEnvelopePropertiesMock.toAmqpBasicProperties()).thenReturn(new BasicProperties());
		Mockito.when(eventEnvelopeMock.getProperties()).thenReturn(eventEnvelopePropertiesMock);
		Mockito.when(eventEnvelopeMock.getEvent()).thenReturn(eventMock);
		Mockito.when(serializationServiceMock.serialize(eventMock)).thenReturn("{type: \"test\"}");
		sink.write(eventEnvelopeMock);
		Mockito.verify(channelMock).basicPublish("test", "test.test", eventEnvelopePropertiesMock.toAmqpBasicProperties(),
				"{type: \"test\"}".getBytes());
		Mockito.verify(eventEnvelopeMock).markAsProcessedSuccessfull();
	}

}
