package com.sarehub.client.amqp;

import java.nio.ByteBuffer;

import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.sarehub.client.event.BasicEventEnvelope;
import com.sarehub.client.event.Event;
import com.sarehub.client.event.EventDeserializationService;
import com.sarehub.client.event.EventDeserializeException;
import com.sarehub.client.event.EventEnvelope;

public class AmqpEventEnvelopeFactory {

	protected EventDeserializationService<ByteBuffer> deserializationService;
	protected AmqpProcessPromiseFactory processPromiseFactory;

	public AmqpEventEnvelopeFactory(EventDeserializationService<ByteBuffer> deserializationService,
			AmqpProcessPromiseFactory processPromiseFactory) {
		this.deserializationService = deserializationService;
		this.processPromiseFactory = processPromiseFactory;
	}

	public EventEnvelope createFromDelivery(Delivery delivery) throws AmqpException {
		try {
			Event event = deserializationService.deserialize(ByteBuffer.wrap(delivery.getBody()));
			BasicEventEnvelope envelope = new BasicEventEnvelope(event, new AmqpEventEnvelopeProperties(delivery));
			envelope.setProcessPromise(processPromiseFactory.create((delivery.getEnvelope().getDeliveryTag())));
			return envelope;
		} catch (EventDeserializeException e) {
			throw new AmqpException("Error when creating new event envelope from delivery", e);
		}
	}

}
