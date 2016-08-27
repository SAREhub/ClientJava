package com.sarehub.client.event;

import java.nio.ByteBuffer;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * JSON event serialization service, used Gson lib for serialize events.
 */
public class JsonEventSerializationService implements EventSerializationService<ByteBuffer> {

	private HashMap<String, EventSerializer<JsonObject>> serializerRegistry;
	private Gson gson;

	public JsonEventSerializationService(Gson gson) {
		serializerRegistry = new HashMap<String, EventSerializer<JsonObject>>();
		this.gson = gson;
	}

	/**
	 * Register serializer for eventType
	 * 
	 * @param eventType
	 * @param serializer
	 */
	public void registerSerializer(String eventType, EventSerializer<JsonObject> serializer) {
		serializerRegistry.put(eventType, serializer);
	}

	public ByteBuffer serialize(Event event) throws EventSerializeException {
		EventSerializer<JsonObject> serializer = getSerializer(event);
		if (serializer == null) {
			throw new EventSerializeException("Serializer for event type: " + event.getEventType() + " isn't registered");
		}

		JsonObject eventData = serializer.serialize(event);
		if (!eventData.has("type")) {
			throw new EventSerializeException("Event data must contains member called 'type' for deserialize purpose");
		}
		return ByteBuffer.wrap(gson.toJson(eventData).getBytes());
	}

	/**
	 * Returns registered serializer for event or null
	 * 
	 * @param event
	 * @return
	 */
	public EventSerializer<JsonObject> getSerializer(Event event) {
		return getSerializer(event.getEventType().getName());
	}

	/**
	 * Returns registered serializer for event or null
	 * 
	 * @param eventType
	 * @return
	 */
	public EventSerializer<JsonObject> getSerializer(String eventType) {
		return serializerRegistry.get(eventType);
	}

}
