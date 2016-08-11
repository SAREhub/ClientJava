package com.sarehub.client.amqp;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Envelope;
import com.sarehub.client.event.EventEnvelopeProperties;

public class AmqpEventEnvelopeProperties implements EventEnvelopeProperties {

	private RoutingKey routingKey;

	private String replyTo;

	private String correlationId;

	private int priority;

	private Envelope amqpDeliveryEnvelope;

	public RoutingKey getRoutingKey() {
		return routingKey;
	}

	public String getReplyTo() {
		return replyTo;
	}

	public boolean hasReplyTo() {
		return this.replyTo != null;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public boolean hasCorrelationId() {
		return this.correlationId != null;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public int getPriority() {
		return priority;
	}

	public boolean hasPriority() {
		return this.priority != 0;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public long getDeliveryTag() {
		return amqpDeliveryEnvelope.getDeliveryTag();
	}

	public BasicProperties toAmqpBasicProperties() {
		return null;

	}
}
