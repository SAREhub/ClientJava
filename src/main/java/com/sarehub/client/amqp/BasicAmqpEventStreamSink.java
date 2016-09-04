package com.sarehub.client.amqp;

import java.nio.ByteBuffer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.sarehub.client.event.Event;
import com.sarehub.client.event.EventEnvelope;
import com.sarehub.client.event.EventSerializationService;
import com.sarehub.client.event.EventSerializeException;
import com.sarehub.client.event.EventStreamSink;
import com.sarehub.client.event.EventStreamSource;

public class BasicAmqpEventStreamSink implements EventStreamSink {

	public final static String PUBLISH_EXCHANGE_PREFIX = "PC";

	private Channel channel;
	private String exchange;
	private EventSerializationService<ByteBuffer> serializationService;

	public BasicAmqpEventStreamSink(Channel channel, String exchange,
			EventSerializationService<ByteBuffer> serializationService) {
		this.channel = channel;
		this.exchange = exchange;
		this.serializationService = serializationService;
	}

	@Override
	public void write(EventEnvelope eventEnvelope) {
		try {
			AmqpEventEnvelopeProperties properties = (AmqpEventEnvelopeProperties) eventEnvelope.getProperties();
			String routingKey = properties.getRoutingKey().toString();
			AMQP.BasicProperties amqpProperties = properties.toAmqpBasicProperties();
			channel.basicPublish(exchange, routingKey, amqpProperties, serializeEvent(eventEnvelope.getEvent()));
			eventEnvelope.markAsProcessedSuccessfull();
		} catch (Exception e) {
			eventEnvelope.markAsProcessedExceptionally(e);
		}
	}

	private byte[] serializeEvent(Event event) throws EventSerializeException {
		return serializationService.serialize(event).array();
	}

	@Override
	public void onPipe(EventStreamSource source) {
	}

	@Override
	public void onUnpipe(EventStreamSource source) {

	}

	public Channel getChannel() {
		return channel;
	}

	public String getExchange() {
		return exchange;
	}
}
