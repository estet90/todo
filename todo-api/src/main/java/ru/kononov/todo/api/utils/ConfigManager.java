package ru.kononov.todo.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.kononov.todo.api.entities.exceptions.TodoException;
import ru.kononov.todo.api.entities.exceptions.TodoExceptionCode;

@ApplicationScoped
public class ConfigManager implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LogManager.getLogger(ConfigManager.class);
	
	public static final String MONGO_DB_HOST_PARAM_NAME = "mongo.db.host";
	public static final String MONGO_DB_PORT_PARAM_NAME = "mongo.db.port";
	public static final String MONGO_DB_NAME_PARAM_NAME = "mongo.db.name";

	private Properties PROPERTIES = new Properties();
	private static final String PROPERTIES_FILE_NAME = "config.properties";

	@PostConstruct
	private void init(){
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);
			PROPERTIES.load(inputStream);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	public String getProperty(String propertyName) throws TodoException{
		String property = PROPERTIES.getProperty(propertyName);
		if (property == null){
			throw new TodoException(TodoExceptionCode.UNKNOWN_PROPERTY_EXCEPTION, propertyName);
		}
		return property;
	}

}
