package com.sarehub.client.user;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.Arrays;

import org.junit.Test;

public class UserTest {

	@Test
	public void testFindKeyByType() {
		EmailUserKey foundKey = new EmailUserKey("example@example.com");
		User user = new User(Arrays.asList(foundKey, new CookieUserKey("test123")));
		assertSame(foundKey, user.findKeyByType(EmailUserKey.class));
	}

	@Test
	public void testFindKeyByTypeWhenKeyCantBeFound() {
		EmailUserKey foundKey = new EmailUserKey("example@example.com");
		User user = new User(Arrays.asList(foundKey, new CookieUserKey("test123")));
		assertNull(user.findKeyByType(MobileUserKey.class));
	}

}
