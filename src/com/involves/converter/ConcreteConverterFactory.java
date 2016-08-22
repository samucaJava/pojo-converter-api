package com.involves.converter;

import com.involves.json.PojoToJSON;

public class ConcreteConverterFactory extends ConverterFactory {

	@Override
	public PojoConverter getConverter(String type) {

		switch (type) {
		case "JSON":
			return new PojoToJSON();
		default:
			break;
		}
		return null;
	}

}
