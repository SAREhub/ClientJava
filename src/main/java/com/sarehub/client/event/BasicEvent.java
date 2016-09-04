package com.sarehub.client.event;

public class BasicEvent implements Event {

	private Object underlying;
	private EventType eventType;

	/**
	 * Returns the value of an event property for the given property name.
	 * 
	 * @param propertyName
	 */
	public Object getProperty(String propertyName) {
		return null;
	}

	public EventType getEventType() {
		return eventType;
	}

	public Object getUnderlying() {
		return underlying;
	}
}
