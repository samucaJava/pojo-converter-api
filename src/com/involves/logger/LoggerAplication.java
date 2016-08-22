package com.involves.logger;

import java.util.logging.Logger;

public abstract class LoggerAplication {

	public static void logger(Class<?> clazz, String msg) {
		Logger.getLogger(clazz.getName()).info(msg);
	}

}
