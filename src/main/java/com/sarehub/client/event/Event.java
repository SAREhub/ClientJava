package com.sarehub.client.event;

/**
 * Event representation. All events have an EventType. Events also usually have one or more event
 * properties. This interface allows the querying of event type, event property values and the
 * underlying event object.
 */
public interface Event {

	/**
	 * Returns the value of an event property for the given property name.
	 * 
	 * @param propertyName
	 */
	public Object getProperty(String propertyName);

	/**
	 * Returns EventType for instance that describes the set of properties available for this event.
	 */
	public EventType getEventType();

	/**
	 * Get the underlying data object to this event wrapper.
	 */
	public Object getUnderlying();
}
