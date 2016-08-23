package com.involves.json;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.involves.converter.Convertable;
import com.involves.logger.LoggerAplication;

public class JSONGenerator {

	private String jsonString;
	private static final String BEGIN = "{";
	private static final String END = "}";
	private static final String DOUBLEQUOTE = "\"";
	private static final String COLON = ":";
	private static final String COMMA = ",";
	private static final String OPENBRACKETS = "[";
	private static final String CLOSEBRACKETS = "]";

	private List<JSONSimpleData> jsonSimpleDatas = new ArrayList<>();
	private List<JSONObject> jsonObjects = new ArrayList<>();
	private List<JSONListObject> jsonListObjects = new ArrayList<>();
	private List<JSONArray> jsonArrays = new ArrayList<>();

	public String generatorJSON() {
		LoggerAplication.logger(this.getClass(), "Starting JSON conversion.");

		this.jsonString = BEGIN;
		Boolean isExistsPrevious = false;
		if (this.jsonSimpleDatas != null && !this.jsonSimpleDatas.isEmpty()) {
			LoggerAplication.logger(this.getClass(), "Starting Conversion SimpleDatas");

			addSimpleDatas(jsonSimpleDatas);
			isExistsPrevious = true;

			LoggerAplication.logger(this.getClass(), "Finalizing Conversion SimpleDatas");
		}

		if (jsonObjects != null && !jsonObjects.isEmpty()) {
			LoggerAplication.logger(this.getClass(), "Starting Conversion Objects");

			isExistsPrevious = addCommaIfExistsPrevious(isExistsPrevious);
			addObject(jsonObjects, false);

			LoggerAplication.logger(this.getClass(), "Finalizing Conversion Objects");
		}

		if (jsonListObjects != null && !jsonListObjects.isEmpty()) {
			LoggerAplication.logger(this.getClass(), "Starting Conversion ListObjects");

			isExistsPrevious = addCommaIfExistsPrevious(isExistsPrevious);
			addListObject();

			LoggerAplication.logger(this.getClass(), "Finalizing Conversion ListObjects");
		}

		if (jsonArrays != null && !jsonArrays.isEmpty()) {
			LoggerAplication.logger(this.getClass(), "Starting Conversion Arrays");

			if (isExistsPrevious) {
				addComma();
			}
			addArrays();

			LoggerAplication.logger(this.getClass(), "Finalizing Conversion Arrays");
		}

		return jsonString.concat(END);
	}

	private Boolean addCommaIfExistsPrevious(Boolean isExistsPrevious) {
		if (isExistsPrevious) {
			addComma();
		}
		isExistsPrevious = true;
		return isExistsPrevious;
	}

	private void addArrays() {

		for (JSONArray jsonArray : jsonArrays) {

			addWithDoubleQuote(jsonArray.getKey());
			addColon();
			addOpenBracket();
			int count = 1;
			int listSize = jsonArray.getJsonValues().size();

			LoggerAplication.logger(this.getClass(), "Adding Arrays to JSON");
			for (Object value : jsonArray.getJsonValues()) {
				addValue(value);
				if (count < listSize)
					addComma();
				count++;
			}
			addCloseBracket();
		}

	}

	private void addOpenBracket() {
		jsonString = jsonString.concat(OPENBRACKETS);
	}

	private void addCloseBracket() {
		jsonString = jsonString.concat(CLOSEBRACKETS);
	}

	private void addListObject() {
		int listSize = jsonListObjects.size();
		int count = 1;
		LoggerAplication.logger(this.getClass(), "Adding ListObjects to JSON");
		for (JSONListObject jsonListObject : jsonListObjects) {

			addWithDoubleQuote(jsonListObject.getKey());
			addColon();

			addObject(jsonListObject.getJsonObjects(), true);

			if (count < listSize)
				addComma();

			count++;
		}
	}

	private void addObject(List<JSONObject> jsonObjects, Boolean isObjectInsideList) {
		int listSize = jsonObjects.size();
		int count = 1;
		LoggerAplication.logger(this.getClass(), "Adding Objects to JSON");
		for (JSONObject jsonObject : jsonObjects) {
			if (!jsonObject.getKey().isEmpty()) {
				addWithDoubleQuote(jsonObject.getKey());
				addColon();
			}
			addBeginObject(!isObjectInsideList || (count == 1 && isObjectInsideList));
			addSimpleDatas(jsonObject.getJsonSimpleDatas());
			addEndObject(!isObjectInsideList || (count == listSize && isObjectInsideList));
			if (count < listSize)
				addComma();
			count++;
		}
	}

	private void addBeginObject(Boolean isWithBracktes) {
		if (isWithBracktes) {
			jsonString = jsonString.concat(OPENBRACKETS).concat(BEGIN);
		} else {
			jsonString = jsonString.concat(BEGIN);
		}
	}

	private void addEndObject(Boolean isWithBracktes) {
		if (isWithBracktes) {
			jsonString = jsonString.concat(END).concat(CLOSEBRACKETS);
		} else {
			jsonString = jsonString.concat(END);
		}

	}

	private void addSimpleDatas(List<JSONSimpleData> jsonSimpleDatas) {
		int listSize = jsonSimpleDatas.size();
		int count = 1;
		LoggerAplication.logger(this.getClass(), "Adding SimpleDatas to JSON");
		for (JSONSimpleData jsonSimpleData : jsonSimpleDatas) {

			addWithDoubleQuote(jsonSimpleData.getKey());
			addColon();
			addValue(jsonSimpleData.getValue());
			if (count < listSize)
				addComma();

			count++;
		}
	}

	private void addComma() {
		jsonString = jsonString.concat(COMMA);
	}

	private void addColon() {
		jsonString = jsonString.concat(COLON);
	}

	private void addValue(Object value) {
		String simpleNameClass = value.getClass().getSimpleName();
		if (simpleNameClass.equals("String")) {
			addWithDoubleQuote(value.toString());
		} else if (isNumber(simpleNameClass)) {
			addWithoutDoubleQuote(value.toString());
		}
	}

	private void addWithDoubleQuote(String data) {
		jsonString = jsonString.concat(DOUBLEQUOTE).concat(data).concat(DOUBLEQUOTE);
	}

	private void addWithoutDoubleQuote(String data) {
		jsonString = jsonString.concat(data);
	}

	private boolean isNumber(String simpleNameClass) {
		return simpleNameClass.equals("Integer") || simpleNameClass.equals("Double")
				|| simpleNameClass.equals("BigDecimal") || simpleNameClass.equals("Long");
	}

	public List<JSONSimpleData> getJsonSimpleDatas() {
		return jsonSimpleDatas;
	}

	public void setJsonSimpleDatas(List<JSONSimpleData> jsonSimpleDatas) {
		this.jsonSimpleDatas = jsonSimpleDatas;
	}

	public List<JSONObject> getJsonObjects() {
		return jsonObjects;
	}

	public void setJsonObjects(List<JSONObject> jsonObjects) {
		this.jsonObjects = jsonObjects;
	}

	public List<JSONListObject> getJsonListObjects() {
		return jsonListObjects;
	}

	public void setJsonListObjects(List<JSONListObject> jsonListObjects) {
		this.jsonListObjects = jsonListObjects;
	}

	public List<JSONArray> getJsonArrays() {
		return jsonArrays;
	}

	public void setJsonArrays(List<JSONArray> jsonArrays) {
		this.jsonArrays = jsonArrays;
	}

	public void addJsonSimpleDatas(Field field, Object value) {
		this.jsonSimpleDatas.add(new JSONSimpleData.JSONSimpleDataBuilder().key(field.getName()).value(value).build());
	}

	public void addJsonObjects(Field field, Object value) {

		List<JSONSimpleData> jsonSimpleDatas = getSimpleDataInformation(value);
		JSONObject jsonObject = new JSONObject.JSONObjectBuilder().key(field.getName()).jsonSimpleDatas(jsonSimpleDatas)
				.build();
		jsonObjects.add(jsonObject);

	}

	public void addJsonListObjecs(Field field, Object obj) {

		@SuppressWarnings("unchecked")
		List<Convertable> list = (List<Convertable>) obj;
		List<JSONObject> jsonObjects = new ArrayList<>();

		for (Convertable value : list) {
			List<JSONSimpleData> jsonSimpleDatas = getSimpleDataInformation(value);
			JSONObject jsonObject = new JSONObject.JSONObjectBuilder().jsonSimpleDatas(jsonSimpleDatas).build();
			jsonObjects.add(jsonObject);

		}
		JSONListObject jsonListObject = new JSONListObject.JSONListObjectBuilder().key(field.getName())
				.jsonObjects(jsonObjects).build();
		jsonListObjects.add(jsonListObject);
	}

	private List<JSONSimpleData> getSimpleDataInformation(Object value) {
		List<JSONSimpleData> jsonSimpleDatas = new ArrayList<>();

		Class<?> clazz = value.getClass();
		for (Field childField : value.getClass().getDeclaredFields()) {
			childField.setAccessible(true);

			try {
				Method method = clazz.getMethod("get" + childField.getName().replaceFirst("^[a-z]",
						childField.getName().substring(0, 1).toUpperCase()));
				JSONSimpleData jsonSimpleData = new JSONSimpleData(childField.getName(), method.invoke(value));
				jsonSimpleDatas.add(jsonSimpleData);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
		return jsonSimpleDatas;
	}

	public void addJsonArray(Field field, Object obj) {
		Object[] objs = (Object[]) obj;
		JSONArray jsonArray = new JSONArray(field.getName());
		for (Object object : objs) {
			jsonArray.add(object);
		}
		jsonArrays.add(jsonArray);
	}

	public String unionObjects(String key, List<String> jsons) {
		int listSize = jsons.size();
		int count = 1;
		String completeJson = BEGIN.concat(DOUBLEQUOTE).concat(key).concat(DOUBLEQUOTE).concat(COLON)
				.concat(OPENBRACKETS);

		for (String json : jsons) {
			completeJson = completeJson.concat(json);
			if (count < listSize) {
				completeJson = completeJson.concat(COMMA);
			}
			count++;

		}
		completeJson = completeJson.concat(CLOSEBRACKETS).concat(END);
		return completeJson;
	}
}
