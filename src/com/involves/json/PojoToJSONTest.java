package com.involves.json;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.involves.converter.Convertable;
import com.involves.test.Address;
import com.involves.test.Car;
import com.involves.test.Job;
import com.involves.test.Person;

public class PojoToJSONTest {

	private static final String BANDS = "\"bands\":[\"Iron Maiden\",\"Metallica\",\"Angra\"]";
	private static final String CARS = "\"cars\":[{\"model\":\"Vectra\",\"year\":2015,\"color\":\"Azul\",\"price\":18,\"tankCapacity\":45.5},{\"model\":\"Corsa\",\"year\":2014,\"color\":\"Verde\",\"price\":15,\"tankCapacity\":40.5}]";
	private static final String ADDRESSANDJOB = "\"address\":[{\"street\":\"João Martins de Souza\",\"number\":480}],\"job\":[{\"cargo\":\"Desenvolvedor\",\"empresa\":\"Involves\"}]";
	private static final String BASICSDATA = "\"name\":\"Samuel\",\"age\":28";
	private static final String COMPLETEJSON = "{".concat(BASICSDATA).concat(",").concat(ADDRESSANDJOB).concat(",")
			.concat(CARS).concat(",").concat(BANDS).concat("}");

	@Test
	public void converterJSONWithSimpleDataOnly() {
		PojoToJSON pojoToJSON = new PojoToJSON();

		Person person = new Person.PessoaBuilder().name("Samuel").age(28).build();

		assertEquals("{".concat(BASICSDATA).concat("}"), pojoToJSON.converter(person));
	}

	@Test
	public void converterJSONWithObjectOnly() {
		PojoToJSON pojoToJSON = new PojoToJSON();

		Person person = new Person.PessoaBuilder().address(new Address("João Martins de Souza", 480))
				.job(new Job("Desenvolvedor", "Involves")).build();

		assertEquals("{".concat(ADDRESSANDJOB).concat("}"), pojoToJSON.converter(person));
	}

	@Test
	public void converterJSONWithListObjectOnly() {
		PojoToJSON pojoToJSON = new PojoToJSON();

		List<Car> cars = getCarsList();
		Person person = new Person.PessoaBuilder().cars(cars).build();

		assertEquals("{".concat(CARS).concat("}"), pojoToJSON.converter(person));
	}

	@Test
	public void converterJSONWithArrayOnly() {
		PojoToJSON pojoToJSON = new PojoToJSON();

		String[] bands = getBandsList();

		Person person = new Person.PessoaBuilder().bands(bands).build();

		assertEquals("{".concat(BANDS).concat("}"), pojoToJSON.converter(person));
	}

	@Test
	public void converterCompleteJSON() {
		PojoToJSON pojoToJSON = new PojoToJSON();

		Person person = new Person.PessoaBuilder().name("Samuel").age(28)
				.address(new Address("João Martins de Souza", 480)).job(new Job("Desenvolvedor", "Involves"))
				.cars(getCarsList()).bands(getBandsList()).build();

		assertEquals(COMPLETEJSON, pojoToJSON.converter(person));
	}

	@Test
	public void converterCompleteJSONWithOutPutStream() {
		PojoToJSON pojoToJSON = new PojoToJSON();

		Person person = new Person.PessoaBuilder().name("Samuel").age(28)
				.address(new Address("João Martins de Souza", 480)).job(new Job("Desenvolvedor", "Involves"))
				.cars(getCarsList()).bands(getBandsList()).build();
		ByteArrayOutputStream outPutStream = new ByteArrayOutputStream();
		assertEquals(COMPLETEJSON, pojoToJSON.converter(person, outPutStream).toString());
	}

	@Test
	public void converterTwoCompleteJSON() {
		PojoToJSON pojoToJSON = new PojoToJSON();

		List<Convertable> persons = getListPersons();
		assertEquals("{\"person\":[" + COMPLETEJSON + "," + COMPLETEJSON + "]}", pojoToJSON.converter(persons));
	}

	@Test
	public void converterTwoCompleteJSONWithOutPutStream() {
		PojoToJSON pojoToJSON = new PojoToJSON();

		List<Convertable> persons = getListPersons();
		ByteArrayOutputStream outPutStream = new ByteArrayOutputStream();
		assertEquals("{\"person\":[" + COMPLETEJSON + "," + COMPLETEJSON + "]}",
				pojoToJSON.converterAll(persons, outPutStream).toString());
	}

	private List<Convertable> getListPersons() {
		Person person1 = new Person.PessoaBuilder().name("Samuel").age(28)
				.address(new Address("João Martins de Souza", 480)).job(new Job("Desenvolvedor", "Involves"))
				.cars(getCarsList()).bands(getBandsList()).build();

		Person person2 = new Person.PessoaBuilder().name("Samuel").age(28)
				.address(new Address("João Martins de Souza", 480)).job(new Job("Desenvolvedor", "Involves"))
				.cars(getCarsList()).bands(getBandsList()).build();

		List<Convertable> persons = new ArrayList<>();
		persons.add(person1);
		persons.add(person2);
		return persons;
	}

	private List<Car> getCarsList() {
		List<Car> cars = new ArrayList<>();
		cars.add(new Car("Vectra", 2015, "Azul", new BigDecimal(18.000), 45.5D));
		cars.add(new Car("Corsa", 2014, "Verde", new BigDecimal(15.000), 40.5D));
		return cars;
	}

	private String[] getBandsList() {
		String[] bands = new String[3];
		bands[0] = "Iron Maiden";
		bands[1] = "Metallica";
		bands[2] = "Angra";
		return bands;
	}

}
