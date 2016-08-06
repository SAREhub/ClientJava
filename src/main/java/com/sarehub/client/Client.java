package com.sarehub.client;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

public class Client {

	private String moduleName;
	private Gson gson;
	private JsonParser jsonParser;

	public Client(String moduleName, Gson gson, JsonParser jsonParser) {
		this.moduleName = moduleName;
		this.gson = gson;
		this.jsonParser = jsonParser;
	}

	public String getModuleName() {
		return moduleName;
	}

	public Gson getGson() {
		return gson;
	}

	public JsonParser getJsonParser() {
		return jsonParser;
	}
}
