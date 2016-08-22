package com.involves.converter;

import static org.junit.Assert.*;

import org.junit.Test;

import com.involves.json.PojoToJSON;

public class ConcreteConverterFactoryTest {

	@Test
	public void mustReturnAnInstanceOfPojoToJSON() {
		ConverterFactory converterFactory = new ConcreteConverterFactory();
		PojoConverter pojoConverter = converterFactory.getConverter("JSON");

		assertEquals(PojoToJSON.class.getName(), pojoConverter.getClass().getName());

	}

	@Test
	public void mustReturnNullBecauseParameterIsInvalid() {
		ConverterFactory converterFactory = new ConcreteConverterFactory();
		PojoConverter pojoConverter = converterFactory.getConverter("XXX");

		assertNull(pojoConverter);
	}

}
