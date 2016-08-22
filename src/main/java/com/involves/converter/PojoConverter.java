package com.involves.converter;

import java.io.OutputStream;
import java.util.List;

public interface PojoConverter {

	public OutputStream converter(Convertable obj, OutputStream outputStream);
	
	public OutputStream converterAll(List<Convertable> objs, OutputStream outputStream);
	
}
