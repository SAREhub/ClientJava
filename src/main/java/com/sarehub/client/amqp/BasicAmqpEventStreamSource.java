package com.sarehub.client.amqp;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.rabbitmq.client.ShutdownSignalException;
import com.sarehub.client.event.EventEnvelope;
import com.sarehub.client.event.EventStreamSourceBase;

public class BasicAmqpEventStreamSource extends EventStreamSourceBase {

	private Channel channel;
	private String queueName;
	private DeliveryAmqpEventEnvelopeFactory eventEnvelopeFactory;

	private QueueingConsumer consumer;
	private String consumerTag;

	public BasicAmqpEventStreamSource(Channel channel, String queueName, DeliveryAmqpEventEnvelopeFactory eventEnvelopeFactory) {
		this.channel = channel;
		this.queueName = queueName;
		this.eventEnvelopeFactory = eventEnvelopeFactory;
	}

	public void initFlow() throws AmqpException {
		if (isInFlowMode()) {
			throw new AmqpException("That source is in flow mode");
		}

		try {
			consumer = new QueueingConsumer(channel);
			consumerTag = channel.basicConsume(queueName, false, consumer);
			channel.basicQos(2);
		} catch (IOException e) {
			throw new AmqpException("Can't init source flow mode", e);
		}
	}

	@Override
	public void flow() throws Exception {
		if (isInFlowMode()) {
			try {
				Delivery delivery = consumer.nextDelivery();
				EventEnvelope eventEnvelope = eventEnvelopeFactory.create(delivery);
				this.sink.write(eventEnvelope);
			} catch (ShutdownSignalException | ConsumerCancelledException | InterruptedException e) {
				throw new AmqpException("Error in source flow mode", e);
			}
		}
	}

	public void stopFlow() throws AmqpException {
		if (isInFlowMode()) {
			try {
				channel.basicCancel(consumerTag);
				consumer = null;
			} catch (IOException e) {
				throw new AmqpException("Error when sending stop flow request to source", e);
			}
		}
	}

	public boolean isInFlowMode() {
		return consumer != null;
	}

	public String getQueueName() {
		return queueName;
	}

}
