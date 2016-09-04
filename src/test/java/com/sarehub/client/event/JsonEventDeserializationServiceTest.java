package com.sarehub.client.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

import java.nio.ByteBuffer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonEventDeserializationServiceTest {

	private JsonEventDeserializationService service;

	@Mock
	private EventDeserializer<JsonObject> deserializerMock;

	@Mock
	private Event eventMock;

	@Mock
	private EventType eventTypeMock;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		service = new JsonEventDeserializationService(new JsonParser());
	}

	@Test
	public void testRegisterDeserializer() {
		service.registerDeserializer("test", deserializerMock);
		assertEquals(deserializerMock, service.getDeserializer("test"));
	}

	@Test
	public void testDeserialize() throws EventDeserializeException {
		Gson gson = new Gson();
		JsonObject eventData = new JsonObject();

		when(eventTypeMock.getName()).thenReturn("test");
		when(eventMock.getEventType()).thenReturn(eventTypeMock);
		eventData.addProperty("type", eventMock.getEventType().getName());
		when(deserializerMock.deserialize(Mockito.any(JsonObject.class))).thenReturn(eventMock);

		service.registerDeserializer("test", deserializerMock);
		assertSame(eventMock, service.deserialize(ByteBuffer.wrap(gson.toJson(eventData).getBytes())));
	}

}
