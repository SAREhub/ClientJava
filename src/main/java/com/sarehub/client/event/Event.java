package com.sarehub.client.event;

/**
 * Base class for all events
 */
public abstract class Event {

	/**
	 * Must return event type id string
	 * 
	 * @return
	 */
	public abstract String getEventType();
}
