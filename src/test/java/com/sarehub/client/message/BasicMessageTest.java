package com.sarehub.client.message;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BasicMessageTest {

	private Message message;

	@Before
	public void setUp() throws Exception {
		message = new BasicMessage();
	}

	@Test
	public void testCopy() {
		Message copy = message.copy();
		assertNotNull(copy);
		assertNotSame(message, copy);
	}

	@Test
	public void testCopyWhenNoHeaders() {
		assertNotSame(message.copy().getHeaders(), message.getHeaders());
	}

	@Test
	public void testGetHeaderWhenNotExists() {
		assertNull(message.getHeader("empty"));
	}

	@Test
	public void testGetHeaderWithDefaultValueWhenExists() throws Exception {
		String name = "header";
		String value = "value";
		message.setHeader(name, value);
		assertEquals(value, message.getHeader(name, "default"));
	}

	@Test
	public void testGetHeaderWithDefaultValueWhenNotExists() throws Exception {
		assertEquals("default", message.getHeader("empty", "default"));
	}

	@Test
	public void testGetHeaderWithCast() {
		String name = "header";
		String value = "value";
		message.setHeader(name, value);
		assertEquals(value, message.getHeader(name, String.class));
	}

	@Test
	public void testSetHeader() throws Exception {
		String name = "header";
		String value = "value";

		assertSame(message, message.setHeader(name, value));
		assertEquals(value, message.getHeader(name));
	}

	@Test
	public void testRemoveHeader() throws Exception {
		String name = "header";
		String value = "value";
		message.setHeader(name, value);
		assertSame(message, message.removeHeader(name));
		assertNull(message.getHeader(name));
	}

	@Test
	public void testSetBody() throws Exception {
		String body = "body";
		assertSame(message, message.setBody(body));
		assertTrue(message.hasBody());
		assertEquals(body, message.getBody());
	}

	@Test
	public void testHasBodyWhenNotSets() throws Exception {
		assertFalse(message.hasBody());
	}

	@Test
	public void testGetBodyWithCast() {
		message.setBody("body");
		assertEquals("body", message.getBody(String.class));
	}

}
