package com.sarehub.client.amqp;

import org.apache.commons.lang3.Validate;

public class AmqpConnectionConfig {
	private String host;
	private String vhost;
	private int port;
	private String username;
	private String password;

	public static AmqpConnectionConfig newConfig() {
		return new AmqpConnectionConfig();
	}

	public AmqpConnectionConfig withHost(String host) {
		this.host = host;
		return this;
	}

	public AmqpConnectionConfig withVhost(String vhost) {
		this.vhost = vhost;
		return this;
	}

	public AmqpConnectionConfig withPort(int port) {
		this.port = port;
		return this;
	}

	public AmqpConnectionConfig withUsername(String username) {
		this.username = username;
		return this;
	}

	public AmqpConnectionConfig withPassword(String password) {
		this.password = password;
		return this;
	}

	public String getHost() {
		return host;
	}

	public String getVhost() {
		return vhost;
	}

	public int getPort() {
		return port;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void validate() {
		Validate.notBlank(host, "host can't be blank: %s", host);
		Validate.notBlank(getVhost(), "vhost can't be blank: %s", vhost);
		Validate.inclusiveBetween(1, 65535, port);
		Validate.notBlank(username, "username can't be blank: %s", username);
		Validate.notBlank(password, "password can't be blank: %s", password);
	}
}
