package com.involves.json;

import java.util.ArrayList;
import java.util.List;

public class JSONArray implements JSONable {

	private String key;
	private List<Object> values;

	public JSONArray(String key) {
		this.key = key;
		this.values = new ArrayList<>();
	}

	public JSONArray(String key, List<Object> values) {
		this.key = key;
		this.values = values;
	}

	public List<Object> getJsonValues() {
		return this.values;
	}

	public boolean add(Object object) {
		return this.values.add(object);
	}

	@Override
	public String getKey() {
		return this.key;
	}

}
