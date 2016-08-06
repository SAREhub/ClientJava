package com.sarehub.client.event;

public interface EventDeserializationService<ED> {

	/**
	 * Deserialize event data to Event object form supported by service implementation
	 * 
	 * @param eventData
	 * @return
	 * @throws EventDeserializeException
	 */
	Event deserialize(ED eventData) throws EventDeserializeException;

}