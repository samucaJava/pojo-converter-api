package com.involves.json;

import java.util.ArrayList;
import java.util.List;

public class JSONObject implements JSONable {

	private String key;
	List<JSONSimpleData> jsonSimpleDatas;

	public JSONObject() {
		this.key = "";
		this.jsonSimpleDatas = new ArrayList<>();
	}

	public JSONObject(String key) {
		this.key = key;
		this.jsonSimpleDatas = new ArrayList<>();
	}

	public JSONObject(JSONObjectBuilder builder) {
		this.key = builder.key;
		this.jsonSimpleDatas = builder.jsonSimpleDatas;
	}

	public boolean add(JSONSimpleData jsonSimpleData) {
		return jsonSimpleDatas.add(jsonSimpleData);
	}

	public List<JSONSimpleData> getJsonSimpleDatas() {
		return this.jsonSimpleDatas;
	}

	@Override
	public String getKey() {
		return key;
	}

	public static class JSONObjectBuilder {
		private String key;
		private List<JSONSimpleData> jsonSimpleDatas;

		public JSONObjectBuilder(){
			this.key = "";
		}
		
		public JSONObjectBuilder key(String key) {
			this.key = key;
			return this;
		}

		public JSONObjectBuilder jsonSimpleDatas(List<JSONSimpleData> jsonSimpleDatas) {
			this.jsonSimpleDatas = jsonSimpleDatas;
			return this;
		}

		public JSONObject build() {
			return new JSONObject(this);
		}

	}

}
