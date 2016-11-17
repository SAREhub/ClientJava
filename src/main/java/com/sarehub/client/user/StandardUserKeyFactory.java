package com.sarehub.client.user;

public class StandardUserKeyFactory {

	public static final String ID_KEY_TYPE = "id";
	public static final String COOKIE_KEY_TYPE = "cookie";
	public static final String EMAIL_KEY_TYPE = "email";
	public static final String MOBILE_KEY_TYPE = "mobile";

	public static UserKey id(String value) {
		return create(ID_KEY_TYPE, value);
	}

	public static UserKey cookie(String value) {
		return create(COOKIE_KEY_TYPE, value);
	}

	public static UserKey email(String value) {
		return create(EMAIL_KEY_TYPE, value);
	}

	public static UserKey mobile(String value) {
		return create(MOBILE_KEY_TYPE, value);
	}

	public static UserKey create(String type, Object value) {
		return new BasicUserKey(type, value);
	}
}
