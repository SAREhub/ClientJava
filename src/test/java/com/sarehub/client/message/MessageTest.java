package com.sarehub.client.message;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.sarehub.client.message.Message;
import com.sarehub.client.message.MessageJsonDeserializer;
import com.sarehub.client.message.MessageJsonSerializer;


public class MessageTest {

	@Test
	public void messageSerialization() {
		Message message = new Message();
		
		String type = "testType";
		message.setType(type);
		
		JsonObject user = new JsonObject();
		user.addProperty("cookie", "123456");
		message.setUser(user);
		
		JsonObject params = new JsonObject();
		params.addProperty("test", true);
		message.setParams(params);
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Message.class, new MessageJsonSerializer());
		Gson gson = gsonBuilder.create();
		
		JsonObject expected = new JsonObject();
		expected.add("type", new JsonPrimitive(type));
		expected.add("user", user);
		expected.add("params", params);
		Assert.assertEquals(gson.toJson(expected), gson.toJson(message, Message.class));
	}
	
	@Test
	public void messageDeserialize() {
		String type = "testType";
		
		JsonObject user = new JsonObject();
		user.addProperty("cookie", "123456");
		
		JsonObject params = new JsonObject();
		params.addProperty("test", true);
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.add("type", new JsonPrimitive(type));
		jsonObject.add("user", user);
		jsonObject.add("params", params);
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Message.class, new MessageJsonDeserializer());
		Gson gson = gsonBuilder.create();
		
		Message message = gson.fromJson(gson.toJson(jsonObject), Message.class);
		
		Assert.assertEquals(type, message.getType());
		Assert.assertEquals(user, message.getUser());
		Assert.assertEquals(params, params);
	}
}
