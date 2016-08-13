package com.sarehub.client.amqp;

import org.apache.commons.lang3.Validate;

public class AmqpConnectionConfig {
	public String host;
	public String vhost;
	public int port;
	public String username;
	public String password;

	public AmqpConnectionConfig(AmqpConnectionConfig other) {
		this.host = other.host;
		this.vhost = other.vhost;
		this.port = other.port;
		this.username = other.username;
		this.password = other.password;
	}

	public void validate() {
		Validate.notBlank(host, "host can't be blank: %s", host);
		Validate.notBlank(vhost, "vhost can't be blank: %s", vhost);
		Validate.inclusiveBetween(1, 65535, port);
		Validate.notBlank(username, "username can't be blank: %s", username);
		Validate.notBlank(password, "password can't be blank: %s", password);
	}

	public static class Builder {

		private AmqpConnectionConfig config;

		public Builder host(String host) {
			config.host = host;
			return this;
		}

		public Builder vhost(String vhost) {
			config.vhost = vhost;
			return this;
		}

		public Builder port(int port) {
			config.port = port;
			return this;
		}

		public Builder username(String username) {
			config.username = username;
			return this;
		}

		public Builder password(String password) {
			config.password = password;
			return this;
		}

		public AmqpConnectionConfig build() {
			return new AmqpConnectionConfig(config);
		}

	}
}
