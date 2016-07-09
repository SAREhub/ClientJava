package com.sarehub.client.user;

/**
 * User key as mobile phone number
 */
public class MobileUserKey implements UserKey {

	private String mobile;
	
	public MobileUserKey(String mobile) {
		this.mobile= mobile;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	@Override
	public String getTypeName() {
		return "mobile";
	}

}
