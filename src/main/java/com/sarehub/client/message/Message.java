package com.sarehub.client.message;

import com.google.gson.JsonObject;

public class Message {
	
	private String type;
	private JsonObject user;
	private JsonObject params;
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public JsonObject getUser() {
		return user;
	}
	
	public void setUser(JsonObject user) {
		this.user = user;
	}
	
	public JsonObject getParams() {
		return params;
	}
	
	public void setParams(JsonObject params) {
		this.params = params;
	}
	
}
