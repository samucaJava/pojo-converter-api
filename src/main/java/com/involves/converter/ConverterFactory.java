package com.involves.converter;

public abstract class ConverterFactory {

	public abstract PojoConverter getConverter(Type type);
	
}
