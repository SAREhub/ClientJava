package com.sarehub.client.event;

/**
 * Service for serialize and deserialize Events to push to event bus or save in log
 * 
 * @param <ED>
 *            Event Data type
 */
public interface EventSerializationService<ED> {

	/**
	 * Serialize event object to serialized form supported by service implementation
	 * 
	 * @param event
	 * @return
	 * @throws EventSerializeException
	 */
	public ED serialize(Event event) throws EventSerializeException;

	/**
	 * Deserialize event data to Event object form supported by service implementation
	 * 
	 * @param eventData
	 * @return
	 * @throws EventDeserializeException
	 */
	public Event deserialize(ED eventData) throws EventDeserializeException;
}
