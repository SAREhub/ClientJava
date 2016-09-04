package com.sarehub.client.event;

/**
 * Service for serialize Events to push to event bus or save in log
 * 
 * @param <ED>
 *            Event Data type
 */
public interface EventSerializationService<ED> {

	/**
	 * Serialize event object to serialized form supported by service implementation
	 * 
	 * @param event
	 * @return serialized event data
	 * @throws EventSerializeException
	 */
	public ED serialize(Event event) throws EventSerializeException;
}
