package com.sarehub.client.message;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class MessageJsonDeserializer implements JsonDeserializer<Message> {

	@Override
	public Message deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
		Message message = new Message();
		JsonObject object = element.getAsJsonObject();
		message.setType(object.get("type").getAsString());
		message.setUser(object.get("user").getAsJsonObject());
		message.setParams(object.get("params").getAsJsonObject());
		return message;
	}

}
