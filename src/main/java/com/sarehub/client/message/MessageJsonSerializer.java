package com.sarehub.client.message;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 
 */
public class MessageJsonSerializer implements JsonSerializer<Message> {

	@Override
	public JsonElement serialize(Message src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject object = new JsonObject();
		object.addProperty("type", src.getType());
		object.add("user", src.getUser());
		object.add("params", src.getParams());
		return object;
	}

}
