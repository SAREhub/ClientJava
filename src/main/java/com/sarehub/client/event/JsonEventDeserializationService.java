package com.sarehub.client.event;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class JsonEventDeserializationService implements EventDeserializationService<ByteBuffer> {

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

	public Event deserialize(ByteBuffer eventData) throws EventDeserializeException {
		try {
			JsonObject decodedEventData = parser.parse(new String(eventData.array(), "UTF-8")).getAsJsonObject();
			String eventType = decodedEventData.get("type").getAsString();
			if (eventType == null) {
				throw new EventDeserializeException("Event data must contains member called 'type' ");
			}

			EventDeserializer<JsonObject> deserializer = getDeserializer(eventType);
			if (deserializer == null) {
				throw new EventDeserializeException("Deserializer for event type: " + eventType + " isn't registered");
			}

			return deserializer.deserialize(decodedEventData);

		} catch (JsonParseException | UnsupportedEncodingException e) {
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
		return getDeserializer(event.getEventType().getName());
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
