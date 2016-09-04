package com.sarehub.client.event;

/**
 * Interface for implements Event data deserialize to event objects
 * 
 * @param <E>
 *            Event type
 */
public interface EventDeserializer<ED> {

	/**
	 * Deserialize raw event data to Event object of specified Event class
	 * 
	 * @param eventData
	 *            Raw event data
	 * @return
	 */
	public Event deserialize(ED eventData);

}
