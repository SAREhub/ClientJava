package com.sarehub.client.event;

import java.util.HashMap;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class JsonEventDeserializationService implements EventDeserializationService<String> {

	private HashMap<String, EventDeserializer<JsonObject>> deserializerRegistry;
	private JsonParser parser;

	public JsonEventDeserializationService(JsonParser parser) {
		deserializerRegistry = new HashMap<String, EventDeserializer<JsonObject>>();
		this.parser = parser;

	}

	/**
	 * Register deserializer for eventType
	 * 
	 * @param eventType
	 * @param deserializer
	 */
	public void registerDeserializer(String eventType, EventDeserializer<JsonObject> deserializer) {
		deserializerRegistry.put(eventType, deserializer);
	}

	public Event deserialize(String eventData) throws EventDeserializeException {
		try {
			JsonObject decodedEventData = parser.parse(eventData).getAsJsonObject();
			String eventType = decodedEventData.get("type").getAsString();
			if (eventType == null) {
				throw new EventDeserializeException("Event data must contains member called 'type' ");
			}

			EventDeserializer<JsonObject> deserializer = getDeserializer(eventType);
			if (deserializer == null) {
				throw new EventDeserializeException("Deserializer for event type: " + eventType + " isn't registered");
			}

			return deserializer.deserialize(decodedEventData);

		} catch (JsonParseException e) {
			throw new EventDeserializeException(e);
		}
	}

	/**
	 * Returns registered deserializer for event or null
	 * 
	 * @param event
	 * @return
	 */
	public EventDeserializer<JsonObject> getDeserializer(Event event) {
		return getDeserializer(event.getEventType());
	}

	/**
	 * Returns registered deserializer for event or null
	 * 
	 * @param eventType
	 * @return
	 */
	public EventDeserializer<JsonObject> getDeserializer(String eventType) {
		return deserializerRegistry.get(eventType);
	}
}
