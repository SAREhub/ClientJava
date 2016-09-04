package com.sarehub.client.amqp;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.sarehub.client.event.EventEnvelopeProperties;

/**
 * Represents event envelope properties from AMQP message
 */
public class AmqpEventEnvelopeProperties implements EventEnvelopeProperties {

	protected String exchangeName;

	protected RoutingKey routingKey;

	protected String replyTo;

	protected String correlationId;

	protected int priority;

	protected long deliveryTag;

	public AmqpEventEnvelopeProperties() {

	}

	public AmqpEventEnvelopeProperties(Delivery delivery) {

		this.exchangeName = delivery.getEnvelope().getExchange();
		this.routingKey = new RoutingKey(delivery.getEnvelope().getRoutingKey());
		this.deliveryTag = delivery.getEnvelope().getDeliveryTag();

		this.replyTo = delivery.getProperties().getReplyTo();
		this.correlationId = delivery.getProperties().getCorrelationId();
		this.priority = delivery.getProperties().getPriority();
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public boolean hasExchangeName() {
		return exchangeName != null;
	}

	public RoutingKey getRoutingKey() {
		return routingKey;
	}

	public String getReplyTo() {
		return replyTo;
	}

	public boolean hasReplyTo() {
		return this.replyTo != null;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public boolean hasCorrelationId() {
		return this.correlationId != null;
	}

	public int getPriority() {
		return priority;
	}

	public boolean hasPriority() {
		return this.priority != 0;
	}

	public long getDeliveryTag() {
		return deliveryTag;
	}

	public boolean hasDeliveryTag() {
		return deliveryTag != 0;
	}

	public BasicProperties toAmqpBasicProperties() {
		BasicProperties.Builder builder = new BasicProperties.Builder();
		builder.replyTo(replyTo);
		builder.correlationId(correlationId);
		builder.priority(priority);
		return builder.build();
	}

	public static class Builder {

		private RoutingKey routingKey;
		private String replyTo;
		private String correlationId;
		private int priority;

		public Builder routingKey(RoutingKey routingKey) {
			this.routingKey = routingKey;
			return this;
		}

		public Builder replyTo(String replyTo) {
			this.replyTo = replyTo;
			return this;
		}

		public Builder correlationId(String correlationId) {
			this.correlationId = correlationId;
			return this;
		}

		public Builder priority(int priority) {
			this.priority = priority;
			return this;
		}

		public AmqpEventEnvelopeProperties build() {
			AmqpEventEnvelopeProperties properties = new AmqpEventEnvelopeProperties();
			properties.routingKey = routingKey;
			properties.replyTo = replyTo;
			properties.correlationId = correlationId;
			properties.priority = priority;
			return properties;
		}

	}
}
