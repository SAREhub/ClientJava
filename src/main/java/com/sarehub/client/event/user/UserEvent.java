package com.sarehub.client.event.user;

import com.sarehub.client.event.Event;
import com.sarehub.client.user.User;

/**
 * Base class for user events
 */
public abstract class UserEvent extends Event {

	private User user;

	public UserEvent(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

}
