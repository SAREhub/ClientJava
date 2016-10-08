package com.sarehub.client.message;

import static org.junit.Assert.*;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Test;

public class BasicExchangeTest {

	private Exchange exchange;

	@Before
	public void setUp() throws Exception {
		exchange = new BasicExchange();
	}

	@Test
	public void testCopyWhenEmpty() {
		Exchange copy = exchange.copy();
		assertNotSame(exchange, copy);
		assertFalse(copy.hasIn());
		assertFalse(copy.hasOut());
	}

	@Test
	public void testCopyWhenHasIn() {
		exchange.setIn(new BasicMessage());
		Exchange copy = exchange.copy();
		assertNotNull(copy.getIn());
		assertNotSame(exchange.getIn(), copy.getIn());
	}

	@Test
	public void testCopyWhenHasOut() {
		exchange.setOut((new BasicMessage()).setBody("body"));
		Exchange copy = exchange.copy();
		assertNotSame(exchange.getOut(), copy.getOut());
		assertSame(exchange.getOut().getBody(), copy.getOut().getBody());
	}

	@Test
	public void testCopyWhenIsFailed() {
		exchange.setException(new Exception("e"));
		Exchange copy = exchange.copy();
		assertSame(exchange.getException(), copy.getException());
	}

	@Test
	public void testNewWithIn() throws Exception {
		Message in = new BasicMessage();
		assertSame(in, BasicExchange.newWithIn(in).getIn());
	}

	@Test
	public void testGetInWhenNotSet() throws Exception {
		assertNull(exchange.getIn());
	}

	@Test
	public void testHasInWhenNotSet() throws Exception {
		assertFalse(exchange.hasIn());
	}

	@Test
	public void testSetIn() throws Exception {
		Message message = new BasicMessage();
		assertSame(exchange, exchange.setIn(message));
		assertSame(message, exchange.getIn());
	}

	@Test
	public void testHasInWhenSets() throws Exception {
		exchange.setIn(new BasicMessage());
		assertTrue(exchange.hasIn());
	}

	@Test
	public void testGetOutWhenNotSets() throws Exception {
		assertThat(exchange.getOut(), IsInstanceOf.instanceOf(Message.class));
	}

	@Test
	public void testGetOutWhenSets() throws Exception {
		Message message = new BasicMessage();
		exchange.setOut(message);
		assertSame(message, exchange.getOut());
	}

	@Test
	public void testHasOutWhenNotSets() throws Exception {
		assertFalse(exchange.hasOut());
	}

	@Test
	public void testSetOut() throws Exception {
		Message message = new BasicMessage();
		assertSame(exchange, exchange.setOut(message));
		assertSame(message, exchange.getOut());
	}

	@Test
	public void testIsFailedWhenExceptionNotSets() throws Exception {
		assertFalse(exchange.isFailed());
	}

	@Test
	public void testIsFailedWhenExceptionSets() throws Exception {
		exchange.setException(new Exception("e"));
		assertTrue(exchange.isFailed());
	}

}
