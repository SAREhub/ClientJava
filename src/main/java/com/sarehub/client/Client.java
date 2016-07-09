package com.sarehub.client;

import com.google.gson.Gson;

public class Client {
	
	private String moduleName;
	private Gson gson;
	
	public Client(String moduleName, Gson gson) {
		this.moduleName = moduleName;
		this.gson = gson;
	}
	
	public String getModuleName() {
		return moduleName;
	}
	
	public Gson getGson() {
		return gson;
	}
}
