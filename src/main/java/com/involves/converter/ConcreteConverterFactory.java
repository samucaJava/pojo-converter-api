package com.involves.converter;

import com.involves.json.PojoToJSON;

public class ConcreteConverterFactory extends ConverterFactory {

	private static final ConverterFactory INSTANCE = new ConcreteConverterFactory();

	public static ConverterFactory getInstance() {
		return INSTANCE;
	}

	@Override
	public PojoConverter getConverter(Type type) {

		switch (type) {
		case JSON:
			return new PojoToJSON();
		default:
			break;
		}
		return null;
	}

}
