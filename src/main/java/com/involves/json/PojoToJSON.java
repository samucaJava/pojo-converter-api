package com.involves.json;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.involves.converter.Convertable;
import com.involves.converter.PojoConverter;

public class PojoToJSON implements PojoConverter {

	@Override
	public OutputStream converter(Convertable obj, OutputStream outputStream) {
		try {
			outputStream.write(converter(obj).getBytes());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return outputStream;
	}

	@Override
	public OutputStream converterAll(List<Convertable> objs, OutputStream outputStream) {
		try {
			outputStream.write(converter(objs).getBytes());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return outputStream;
	}

	public String converter(Convertable object) {

		JSONGenerator generator = new JSONGenerator();

		for (Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Object obj;
			try {
				obj = field.get(object);
				if (obj != null) {
					Class<?> clazz = obj.getClass();
					if (isImplementationInterface(clazz, Convertable.class)) {
						generator.addJsonObjects(field, obj);
					} else {
						if (isImplementationInterface(clazz, List.class)) {
							generator.addJsonListObjecs(field, obj);
						} else if (clazz.isArray()) {
							generator.addJsonArray(field, obj);
						} else {
							if (clazz.getSimpleName().equals("Integer")) {
								Integer value = new Integer(obj.toString());
								if (value != 0)
									generator.addJsonSimpleDatas(field, obj);
							} else {
								generator.addJsonSimpleDatas(field, obj);
							}

						}
					}
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		return generator.generatorJSON();
	}

	public String converter(List<Convertable> objects) {
		JSONGenerator generator = new JSONGenerator();
		List<String> jsons = new ArrayList<String>();
		String key = "";
		for (Convertable json : objects) {
			jsons.add(converter(json));
			key = json.getClass().getSimpleName().toLowerCase();
		}
		return generator.unionObjects(key, jsons);
	}

	private Boolean isImplementationInterface(Class<?> clazz, Class<?> clazzInterface) {
		Boolean isImplementation = false;
		for (int i = 0; i < clazz.getInterfaces().length; i++) {
			if (clazz.getInterfaces()[i].equals(clazzInterface)) {
				isImplementation = true;
			}
		}
		return isImplementation;
	}

}
