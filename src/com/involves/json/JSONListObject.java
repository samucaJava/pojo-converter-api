package com.involves.json;

import java.util.ArrayList;
import java.util.List;

public class JSONListObject implements JSONable {

	private String key;
	List<JSONObject> jsonObjects;

	public JSONListObject(String key) {
		this.key = key;
		this.jsonObjects = new ArrayList<>();
	}

	public JSONListObject(JSONListObjectBuilder builder) {
		this.key = builder.key;
		this.jsonObjects = builder.jsonObjects;
	}

	public List<JSONObject> getJsonObjects() {
		return jsonObjects;
	}

	public boolean add(JSONObject jsonObject) {
		return this.jsonObjects.add(jsonObject);
	}

	@Override
	public String getKey() {
		return this.key;
	}

	public static class JSONListObjectBuilder {
		private String key;
		private List<JSONObject> jsonObjects;

		public JSONListObjectBuilder key(String key) {
			this.key = key;
			return this;
		}

		public JSONListObjectBuilder jsonObjects(List<JSONObject> jsonObjects) {
			this.jsonObjects = jsonObjects;
			return this;
		}

		public JSONListObject build() {
			return new JSONListObject(this);
		}

	}

}
