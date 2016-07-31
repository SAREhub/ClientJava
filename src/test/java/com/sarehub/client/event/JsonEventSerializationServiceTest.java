package com.sarehub.client.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonEventSerializationServiceTest {

	@Mock
	private EventSerializer<JsonObject> serializerMock;

	@Mock
	private EventDeserializer<JsonObject> deserializerMock;

	@Mock
	private Event eventMock;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testRegisterSerializer() {
		JsonEventSerializationService service = new JsonEventSerializationService(new Gson(), new JsonParser());
		service.registerSerializer("test", serializerMock);
		assertSame(serializerMock, service.getSerializer("test"));
	}

	@Test
	public void testSerialize() throws EventSerializeException {
		Gson gson = new Gson();
		JsonObject eventData = new JsonObject();
		when(eventMock.getEventType()).thenReturn("test");
		eventData.addProperty("type", eventMock.getEventType());

		JsonEventSerializationService service = new JsonEventSerializationService(gson, new JsonParser());

		when(serializerMock.serialize(eventMock)).thenReturn(eventData);
		service.registerSerializer("test", serializerMock);
		assertEquals(gson.toJson(eventData), service.serialize(eventMock));
	}

	@Test
	public void testRegisterDeserializer() {
		JsonEventSerializationService service = new JsonEventSerializationService(new Gson(), new JsonParser());
		service.registerDeserializer("test", deserializerMock);
		assertEquals(deserializerMock, service.getDeserializer("test"));
	}

	@Test
	public void testDeserialize() throws EventDeserializeException {
		Gson gson = new Gson();
		JsonObject eventData = new JsonObject();
		when(eventMock.getEventType()).thenReturn("test");
		eventData.addProperty("type", eventMock.getEventType());

		JsonEventSerializationService service = new JsonEventSerializationService(gson, new JsonParser());
		when(deserializerMock.deserialize(Mockito.any(JsonObject.class))).thenReturn(eventMock);
		service.registerDeserializer("test", deserializerMock);
		assertSame(eventMock, service.deserialize(gson.toJson(eventData)));
	}

}
