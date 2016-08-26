package com.sarehub.client.event.user;

import com.sarehub.client.user.User;

/**
 * Base class for user events
 */
public abstract class UserEvent {

	private User user;

	public UserEvent(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

}
