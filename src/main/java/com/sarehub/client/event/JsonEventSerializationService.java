package com.sarehub.client.event;

import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

/**
 * Json event serialization service, used Gson lib for serialize and deserialize events.
 */
public class JsonEventSerializationService implements EventSerializationService<String> {

	private HashMap<String, EventSerializer<JsonObject>> serializerRegistry;
	private HashMap<String, EventDeserializer<JsonObject>> deserializerRegistry;

	private Gson gson;
	private JsonParser parser;

	public JsonEventSerializationService(Gson gson, JsonParser parser) {

		serializerRegistry = new HashMap<String, EventSerializer<JsonObject>>();
		deserializerRegistry = new HashMap<String, EventDeserializer<JsonObject>>();

		this.gson = gson;
		this.parser = parser;

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

	public String serialize(Event event) throws EventSerializeException {
		EventSerializer<JsonObject> serializer = getSerializer(event);
		if (serializer == null) {
			throw new EventSerializeException("Serializer for event type: " + event.getEventType() + " isn't registered");
		}

		JsonObject eventData = serializer.serialize(event);
		if (!eventData.has("type")) {
			throw new EventSerializeException("Event data must contains member called 'type' for deserialize purpose");
		}
		return gson.toJson(eventData);
	}

	/**
	 * Returns registered serializer for event or null
	 * 
	 * @param event
	 * @return
	 */
	public EventSerializer<JsonObject> getSerializer(Event event) {
		return getSerializer(event.getEventType());
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
