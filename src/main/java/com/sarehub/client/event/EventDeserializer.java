package com.sarehub.client.event;

/**
 * Interface for implements Event data deserialize to event objects
 * 
 * @param <T>
 *            Event data type for better support
 */
public interface EventDeserializer<T> {

	/**
	 * Deserialize raw event data to Event object of specified Event class
	 * 
	 * @param eventData
	 *            Raw event data
	 * @return
	 */
	public Event deserialize(T eventData);

}
