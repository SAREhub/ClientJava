package com.sarehub.client.user;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UserTest {

	@Test
	public void testFindKeyByType() {
		UserKey foundKey = StandardUserKeyFactory.create("test1", "abc");
		List<UserKey> keys = Arrays.asList(foundKey, StandardUserKeyFactory.create("test2", "abc"));

		User user = new User(keys);
		assertSame(foundKey, user.findKeyByType("test1"));
	}

	@Test
	public void testFindKeyByTypeWhenKeyCantBeFound() {
		UserKey foundKey = StandardUserKeyFactory.create("test1", "abc");
		List<UserKey> keys = Arrays.asList(foundKey, StandardUserKeyFactory.create("test2", "abc"));
		User user = new User(keys);
		assertNull(user.findKeyByType("test3"));
	}

}
