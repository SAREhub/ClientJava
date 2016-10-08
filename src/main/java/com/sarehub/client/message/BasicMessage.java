package com.sarehub.client.message;

import java.util.Map;

import org.apache.commons.collections4.map.CaseInsensitiveMap;

public class BasicMessage implements Message {

	private Map<String, Object> headers;

	private Object body;

	public static Message newInstance() {
		return new BasicMessage();
	}

	@Override
	public Message copy() {
		Message copy = newInstance();
		copy.setBody(this.body);

		if (hasAnyHeader()) {
			copy.getHeaders().putAll(copy.getHeaders());
		}

		return copy;
	}

	@Override
	public Object getHeader(String name) {
		return hasAnyHeader() ? headers.get(name) : null;
	}

	@Override
	public Object getHeader(String name, Object defaultValue) {
		Object value = getHeader(name);
		return value != null ? value : defaultValue;
	}

	@Override
	public <T> T getHeader(String name, Class<T> type) {
		Object value = getHeader(name);
		if (value != null) {
			return type.cast(value);
		}

		return null;
	}

	@Override
	public Map<String, Object> getHeaders() {
		if (!hasAnyHeader()) {
			createHeaders();
		}
		return headers;
	}

	private void createHeaders() {
		headers = new CaseInsensitiveMap<String, Object>();
	}

	@Override
	public Message setHeader(String name, Object value) {
		getHeaders().put(name, value);
		return this;
	}

	@Override
	public Message removeHeader(String name) {
		if (hasAnyHeader()) {
			headers.remove(name);
			if (headers.isEmpty()) {
				headers = null;
			}
		}

		return this;
	}

	private boolean hasAnyHeader() {
		return headers != null;
	}

	@Override
	public Object getBody() {
		return body;
	}

	@Override
	public <T> T getBody(Class<T> type) {
		if (hasBody()) {
			return type.cast(body);
		}

		return null;
	}

	@Override
	public boolean hasBody() {
		return body != null;
	}

	@Override
	public Message setBody(Object body) {
		this.body = body;
		return this;
	}

}
