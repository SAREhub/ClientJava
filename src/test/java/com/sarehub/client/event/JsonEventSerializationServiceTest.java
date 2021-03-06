package com.sarehub.client.event;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JsonEventSerializationServiceTest {

	private JsonEventSerializationService service;

	@Mock
	private EventSerializer<JsonObject> serializerMock;

	@Mock
	private Event eventMock;

	@Mock
	private EventType eventTypeMock;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		service = new JsonEventSerializationService(new Gson());
	}

	@Test
	public void testRegisterSerializer() {
		service.registerSerializer("test", serializerMock);
		assertSame(serializerMock, service.getSerializer("test"));
	}

	@Test
	public void testSerialize() throws EventSerializeException {
		Gson gson = new Gson();
		JsonObject eventData = new JsonObject();
		when(eventTypeMock.getName()).thenReturn("test");
		when(eventMock.getEventType()).thenReturn(eventTypeMock);
		eventData.addProperty("type", eventMock.getEventType().getName());
		when(serializerMock.serialize(eventMock)).thenReturn(eventData);

		service.registerSerializer("test", serializerMock);
		assertArrayEquals(gson.toJson(eventData).getBytes(), service.serialize(eventMock).array());
	}

}
