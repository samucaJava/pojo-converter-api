package com.involves.json;

public class JSONSimpleData implements JSONable {

	private String key;
	private Object value;

	public JSONSimpleData(String key, Object value) {
		this.key = key;
		this.value = value;
	}

	public JSONSimpleData(JSONSimpleDataBuilder builder) {
		this.key = builder.key;
		this.value = builder.value;
	}

	public String getKey() {
		return key;
	}

	public Object getValue() {
		return value;
	}

	public static class JSONSimpleDataBuilder {
		private String key;
		private Object value;

		public JSONSimpleDataBuilder key(String key) {
			this.key = key;
			return this;
		}

		public JSONSimpleDataBuilder value(Object value) {
			this.value = value;
			return this;
		}

		public JSONSimpleData build() {
			return new JSONSimpleData(this);
		}

	}

}
