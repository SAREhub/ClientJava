package com.sarehub.client.amqp;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.rabbitmq.client.ShutdownSignalException;
import com.sarehub.client.event.EventEnvelope;
import com.sarehub.client.event.EventStreamSink;
import com.sarehub.client.event.EventStreamSource;
import com.sarehub.client.event.NullEventStreamSink;

public class BasicAmqpEventStreamSource implements EventStreamSource {

	protected String queueName;
	protected QueueingConsumer consumer;
	protected String consumerTag;

	protected AmqpEventEnvelopeFactory eventEnvelopeFactory;

	protected EventStreamSink sink;

	protected boolean inFlowMode;

	protected BasicAmqpEventStreamSource(String queueName) {
		this.queueName = queueName;
		this.sink = new NullEventStreamSink();
	}

	public static BasicAmqpEventStreamSource getFor(String queueName) {
		BasicAmqpEventStreamSource source = new BasicAmqpEventStreamSource(queueName);
		return source;
	}

	public BasicAmqpEventStreamSource amqpConsumer(QueueingConsumer consumer) {
		this.consumer = consumer;
		return this;
	}

	public BasicAmqpEventStreamSource eventEnvelopeFactory(AmqpEventEnvelopeFactory eventEnvelopeFactory) {
		this.eventEnvelopeFactory = eventEnvelopeFactory;
		return this;
	}

	@Override
	public void flow() throws Exception {
		if (!isInFlowMode()) {
			initFlow();
		}

		try {
			Delivery delivery = consumer.nextDelivery();
			EventEnvelope eventEnvelope = eventEnvelopeFactory.createFromDelivery(delivery);
			this.sink.write(eventEnvelope);
		} catch (ShutdownSignalException | ConsumerCancelledException | InterruptedException e) {
			throw new AmqpException("Error in source flow mode", e);
		}
	}

	protected void initFlow() throws AmqpException {
		try {
			checkConfiguration();
			getChannel().basicQos(2);
			consumerTag = getChannel().basicConsume(queueName, false, consumer);
			inFlowMode = true;
		} catch (IOException e) {
			throw new AmqpException("Can't init source flow mode", e);
		}
	}

	protected void checkConfiguration() {
		notNull(this.consumer, "AmqpConsumer can't be null");
		notBlank(this.queueName, "Queue name cant't be blank");
		notNull(this.eventEnvelopeFactory, "Event envelope factory can't be null");
	}

	public void pipe(EventStreamSink sink) {
		this.sink.onUnpipe(this);
		this.sink = sink;
		this.sink.onPipe(this);
	}

	public void unpipe() {
		this.sink.onUnpipe(this);
		this.sink = new NullEventStreamSink();
	}

	public void stopFlow() throws AmqpException {
		if (isInFlowMode()) {
			try {
				getChannel().basicCancel(consumerTag);
				inFlowMode = false;
			} catch (IOException e) {
				throw new AmqpException("Error when sending stop flow request to source", e);
			}
		}
	}

	public boolean isInFlowMode() {
		return inFlowMode;
	}

	public String getQueueName() {
		return queueName;
	}

	public Channel getChannel() {
		return this.consumer.getChannel();
	}

}
