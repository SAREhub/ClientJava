package com.sarehub.client.user;

public class BasicUserKey implements UserKey {

	private String keyType;
	private Object value;

	public BasicUserKey(String keyType, Object value) {
		this.keyType = keyType;
		this.value = value;
	}

	@Override
	public String getKeyType() {
		return keyType;
	}

	@Override
	public Object getValue() {
		return value;
	}

}
