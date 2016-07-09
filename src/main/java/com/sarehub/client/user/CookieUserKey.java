package com.sarehub.client.user;

/**
 * Simple user key from cookie value
 */
public class CookieUserKey implements UserKey {
	
	private String cookie;
	
	public CookieUserKey(String cookie) {
		this.cookie = cookie;
	}
	
	public String getCookie() {
		return cookie;
	}
	
	@Override
	public String getTypeName() {
		return "Cookie";
	}


}
