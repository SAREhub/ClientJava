package com.sarehub.client.amqp;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;

import com.rabbitmq.client.Channel;

public class AmqpProcessPromiseFactory {

	protected Channel channel;

	public AmqpProcessPromiseFactory(Channel channel) {
		this.channel = channel;
	}

	public CompletableFuture<Void> create(long deliveryTag) {
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
