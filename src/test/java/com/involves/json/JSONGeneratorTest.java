package com.involves.json;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.involves.json.JSONArray;
import com.involves.json.JSONGenerator;
import com.involves.json.JSONListObject;
import com.involves.json.JSONObject;
import com.involves.json.JSONSimpleData;
import com.involves.json.JSONListObject.JSONListObjectBuilder;
import com.involves.json.JSONObject.JSONObjectBuilder;
import com.involves.json.JSONSimpleData.JSONSimpleDataBuilder;

public class JSONGeneratorTest {

	private static final String BANDS = "\"bandas\":[\"Iron Maiden\",\"Metallica\",\"Angra\"]";
	private static final String CARS = "\"carros\":[{\"modelo\":\"Vectra\",\"ano\":2015,\"cor\":\"Azul\"},{\"modelo\":\"Corsa\",\"ano\":2014,\"cor\":\"Verde\"}]";
	private static final String ADDRESSANDJOB = "\"endereco\":[{\"logradouro\":\"Rua joão martins de sousa\",\"numero\":480}],\"emprego\":[{\"cargo\":\"Desenvolvedor\",\"empresa\":\"involves\"}]";
	private static final String BASICSDATA = "\"nome\":\"Samuel\",\"idade\":28";
	private static final String COMPLETEJSON = "{".concat(BASICSDATA).concat(",").concat(ADDRESSANDJOB).concat(",")
			.concat(CARS).concat(",").concat(BANDS).concat("}");
	JSONGenerator generator = new JSONGenerator();

	@Test
	public void mustReturnJsonFormatedWithSimpleValue() {
		generator.setJsonSimpleDatas(getJsonSimpleDatas());
		String jsonString = generator.generatorJSON();

		assertEquals("{".concat(BASICSDATA).concat("}"), jsonString);

	}

	@Test
	public void mustReturnJsonFormatedWithObject() {
		generator.setJsonObjects(getJsonObjects());
		String jsonString = generator.generatorJSON();

		assertEquals("{".concat(ADDRESSANDJOB).concat("}"), jsonString);
	}

	@Test
	public void mustReturnJsonFormatedWithListObject() {
		generator.setJsonListObjects(getJSONListObject());
		String jsonString = generator.generatorJSON();

		assertEquals("{".concat(CARS).concat("}"), jsonString);
	}

	@Test
	public void mustReturnJsonFormatedWithListArray() {
		generator.setJsonArrays(getJSONArrayObject());
		String jsonString = generator.generatorJSON();

		assertEquals("{".concat(BANDS).concat("}"), jsonString);
	}

	@Test
	public void mustReturnJsonFormatedWithAllTypes() {
		generator.setJsonSimpleDatas(getJsonSimpleDatas());
		generator.setJsonObjects(getJsonObjects());
		generator.setJsonListObjects(getJSONListObject());
		generator.setJsonArrays(getJSONArrayObject());
		String jsonString = generator.generatorJSON();

		assertEquals(COMPLETEJSON, jsonString);
	}

	private List<JSONSimpleData> getJsonSimpleDatas() {
		List<JSONSimpleData> jsonSimpleDatas = new ArrayList<>();
		jsonSimpleDatas.add(new JSONSimpleData.JSONSimpleDataBuilder().key("nome").value("Samuel").build());
		jsonSimpleDatas.add(new JSONSimpleData.JSONSimpleDataBuilder().key("idade").value(28).build());
		return jsonSimpleDatas;
	}

	private List<JSONObject> getJsonObjects() {
		List<JSONObject> jsonObjects = new ArrayList<>();

		List<JSONSimpleData> addressData = new ArrayList<>();
		List<JSONSimpleData> jobData = new ArrayList<>();

		addressData.add(new JSONSimpleData.JSONSimpleDataBuilder().key("logradouro").value("Rua joão martins de sousa")
				.build());
		addressData.add(new JSONSimpleData.JSONSimpleDataBuilder().key("numero").value(480).build());
		jobData.add(new JSONSimpleData.JSONSimpleDataBuilder().key("cargo").value("Desenvolvedor").build());
		jobData.add(new JSONSimpleData.JSONSimpleDataBuilder().key("empresa").value("involves").build());

		JSONObject address = new JSONObject.JSONObjectBuilder().key("endereco").jsonSimpleDatas(addressData).build();
		JSONObject job = new JSONObject.JSONObjectBuilder().key("emprego").jsonSimpleDatas(jobData).build();

		jsonObjects.add(address);
		jsonObjects.add(job);
		return jsonObjects;
	}

	private List<JSONListObject> getJSONListObject() {

		List<JSONSimpleData> car1Data = new ArrayList<>();
		List<JSONSimpleData> car2Data = new ArrayList<>();

		car1Data.add(new JSONSimpleData.JSONSimpleDataBuilder().key("modelo").value("Vectra").build());
		car1Data.add(new JSONSimpleData.JSONSimpleDataBuilder().key("ano").value(2015).build());
		car1Data.add(new JSONSimpleData.JSONSimpleDataBuilder().key("cor").value("Azul").build());

		car2Data.add(new JSONSimpleData.JSONSimpleDataBuilder().key("modelo").value("Corsa").build());
		car2Data.add(new JSONSimpleData.JSONSimpleDataBuilder().key("ano").value(2014).build());
		car2Data.add(new JSONSimpleData.JSONSimpleDataBuilder().key("cor").value("Verde").build());

		JSONObject car1 = new JSONObject.JSONObjectBuilder().jsonSimpleDatas(car1Data).build();
		JSONObject car2 = new JSONObject.JSONObjectBuilder().jsonSimpleDatas(car2Data).build();

		List<JSONObject> jsonObjects = new ArrayList<>();
		jsonObjects.add(car1);
		jsonObjects.add(car2);

		List<JSONListObject> jsonListObjects = new ArrayList<>();

		jsonListObjects.add(new JSONListObject.JSONListObjectBuilder().key("carros").jsonObjects(jsonObjects).build());

		return jsonListObjects;
	}

	private List<JSONArray> getJSONArrayObject() {
		JSONArray jsonArray = new JSONArray("bandas");
		jsonArray.add("Iron Maiden");
		jsonArray.add("Metallica");
		jsonArray.add("Angra");
		List<JSONArray> jsonArrays = new ArrayList<>();
		jsonArrays.add(jsonArray);
		return jsonArrays;
	}
}
