package com.sarehub.client.user;

import java.util.List;

/**
 * Class for represent user who called event
 */
public class User {

	private List<UserKey> keys;

	public User(List<UserKey> keys) {
		this.keys = keys;
	}
	
	/**
	 * @param userKeyClass
	 * @return Found user key object or null
	 */
	public <T extends UserKey> T findKeyByType(Class<T> userKeyClass) {
		T foundKey = null;
		for (UserKey key : keys) {
			if (userKeyClass.isInstance(key)) {
				foundKey = userKeyClass.cast(userKeyClass);
				break;
			}
		}
		
		return foundKey;
	}
}
