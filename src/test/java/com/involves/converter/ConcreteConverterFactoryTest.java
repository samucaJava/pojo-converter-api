package com.involves.converter;

import static org.junit.Assert.*;

import org.junit.Test;

import com.involves.json.PojoToJSON;

public class ConcreteConverterFactoryTest {

	@Test
	public void mustReturnAnInstanceOfPojoToJSON() {
		PojoConverter pojoConverter = ConcreteConverterFactory.getInstance().getConverter(Type.JSON);

		assertEquals(PojoToJSON.class.getName(), pojoConverter.getClass().getName());

	}

	@Test
	public void mustReturnNullBecauseParameterIsInvalid() {
		PojoConverter pojoConverter = ConcreteConverterFactory.getInstance().getConverter(Type.CVS);

		assertNull(pojoConverter);
	}

}
