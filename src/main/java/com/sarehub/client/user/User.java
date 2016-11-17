/*******************************************************************************
 * Copyright 2016 SARE SA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
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
	 * @param type
	 * @return Found user key object or null
	 */
	public UserKey findKeyByType(String type) {
		for (UserKey key : keys) {
			if (key.getKeyType() == type) {
				return key;
			}
		}

		return null;
	}

}
