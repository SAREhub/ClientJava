package com.sarehub.client.amqp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.sarehub.client.event.BasicEventEnvelope;
import com.sarehub.client.event.Event;
import com.sarehub.client.event.EventDeserializationService;
import com.sarehub.client.event.EventDeserializeException;
import com.sarehub.client.event.EventEnvelope;

public class DeliveryAmqpEventEnvelopeFactory {

	private Channel channel;
	private EventDeserializationService<String> deserializationService;

	public DeliveryAmqpEventEnvelopeFactory(Channel channel, EventDeserializationService<String> deserializationService) {
		this.channel = channel;
		this.deserializationService = deserializationService;
	}

	public EventEnvelope create(Delivery delivery) throws AmqpException {
		try {
			String eventData = new String(delivery.getBody(), "UTF-8");
			Event event = deserializationService.deserialize(eventData);
			BasicEventEnvelope envelope = new BasicEventEnvelope(event, AmqpEventEnvelopeProperties.createFromDelivery(delivery));
			envelope.setProcessPromise(createProcessPromise(delivery.getEnvelope().getDeliveryTag()));
			return envelope;
		} catch (UnsupportedEncodingException | EventDeserializeException e) {
			throw new AmqpException("Error when creating new event envelope from delivery", e);
		}

	}

	private CompletableFuture<Void> createProcessPromise(long deliveryTag) {
		CompletableFuture<Void> processPromise = new CompletableFuture<>();
		processPromise.whenComplete((Void result, Throwable error) -> {
			try {
				if (error != null) {
					boolean requeue = error instanceof CancellationException;
					channel.basicReject(deliveryTag, requeue);
				} else {
					channel.basicAck(deliveryTag, false);
				}
			} catch (IOException e) {
			}
		});

		return processPromise;
	}
}
