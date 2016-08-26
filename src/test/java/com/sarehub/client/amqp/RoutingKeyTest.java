package com.sarehub.client.amqp;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RoutingKeyTest {

	@Test
	public void createFromString() {
		RoutingKey routingKey = new RoutingKey("part1.part2");
		assertArrayEquals(new String[] { "part0", "part1" }, routingKey.getParts());
	}

	@Test
	public void isEmpty() {
		RoutingKey routingKey = new RoutingKey();
		assertTrue(routingKey.isEmpty());
	}

	@Test
	public void isNotEmpty() {
		RoutingKey routingKey = new RoutingKey(new String[] { "test" });
		assertFalse(routingKey.isEmpty());
	}

	@Test
	public void addFirstPart() {
		String part0 = "part0";
		RoutingKey routingKey = new RoutingKey();
		routingKey.addPart(part0);
		assertEquals(part0, routingKey.getPart(0));
	}

	@Test
	public void addTwoParts() {
		String part0 = "part0";
		String part1 = "part1";
		RoutingKey routingKey = new RoutingKey();
		routingKey.addPart(part0);
		routingKey.addPart(part1);
		assertArrayEquals(new String[] { part0, part1 }, routingKey.getParts());
	}

	@Test
	public void onePartKeyToString() {
		RoutingKey routingKey = new RoutingKey(new String[] { "part0" });
		assertEquals("part0", routingKey.toString());
	}

	@Test
	public void twoPartsKeyToString() {
		RoutingKey routingKey = new RoutingKey(new String[] { "part0", "part1" });
		assertEquals("part0.part1", routingKey.toString());
	}
}
