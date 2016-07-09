package com.sarehub.client.user;

import org.apache.commons.lang3.Validate;

/**
 * User key as email address
 */
public class EmailUserKey implements UserKey {

	private String email;
	
	public EmailUserKey(String email) {
		Validate.notBlank(email, "email can't be empty: %s", email);
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	@Override
	public String getTypeName() {
		return "email";
	}

}
