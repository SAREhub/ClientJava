package com.sarehub.client.amqp;

import com.rabbitmq.client.Channel;
import com.sarehub.client.event.Event;
import com.sarehub.client.event.EventEnvelope;
import com.sarehub.client.event.EventSerializationService;
import com.sarehub.client.event.EventSerializeException;
import com.sarehub.client.event.EventStreamSink;
import com.sarehub.client.event.EventStreamSource;

public class BasicAmqpEventStreamSink<E extends Event> implements EventStreamSink<E> {

	public final static String PUBLISH_EXCHANGE_PREFIX = "PC";

	private Channel channel;
	private String exchangeName;
	private EventSerializationService<String> serializationService;

	public BasicAmqpEventStreamSink(Channel channel, String exchangeName,
			EventSerializationService<String> serializationService) {
		this.channel = channel;
		this.exchangeName = exchangeName;
		this.serializationService = serializationService;
	}

	@Override
	public void write(EventEnvelope<E> eventEnvelope) {
		try {
			AmqpEventEnvelopeProperties properties = (AmqpEventEnvelopeProperties) eventEnvelope.getProperties();
			String routingKey = properties.getRoutingKey().toString();
			channel.basicPublish(exchangeName, routingKey, properties.toAmqpBasicProperties(),
					serializeEvent(eventEnvelope.getEvent()));
			eventEnvelope.markAsProcessedSuccessfull();
		} catch (Exception e) {
			eventEnvelope.markAsProcessedExceptionally(e);
		}
	}

	private byte[] serializeEvent(Event event) throws EventSerializeException {
		return serializationService.serialize(event).getBytes();
	}

	@Override
	public void onPipe(EventStreamSource<E> source) {
	}

	@Override
	public void onUnpipe(EventStreamSource<E> source) {

	}

	public Channel getChannel() {
		return channel;
	}

	public String getExchangeName() {
		return exchangeName;
	}
}
