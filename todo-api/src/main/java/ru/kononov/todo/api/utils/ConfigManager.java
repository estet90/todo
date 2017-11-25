package ru.kononov.todo.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.kononov.todo.api.exceptions.TodoException;
import ru.kononov.todo.api.exceptions.TodoExceptionCode;

/**
 * класс для работы с конфигурационными файлами
 * 
 * @author admin
 *
 */
@ApplicationScoped
public class ConfigManager implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LogManager.getLogger(ConfigManager.class);
	
	public static final String MONGO_DB_HOST_PARAM_NAME = "mongo.db.host";
	public static final String MONGO_DB_PORT_PARAM_NAME = "mongo.db.port";
	public static final String MONGO_DB_NAME_PARAM_NAME = "mongo.db.name";

	private Properties PROPERTIES = new Properties();
	private static final String PROPERTIES_FILE_NAME = "config.properties";
	
	/**
	 * основной конструктор без параметров
	 */
	public ConfigManager(){}
	
	/**
	 * коструктор, используемый в юнит-тестах
	 * 
	 * @param configFileName
	 */
	ConfigManager(String configFileName) {
		init(configFileName);
	}
	
	/**
	 * считывание параметров из файла по умолчанию
	 */
	@PostConstruct
	private void init(){
		init(PROPERTIES_FILE_NAME);
	}
	
	/**
	 * считывание параметров из указанного файла
	 * @param configFileName
	 */
	private void init(String configFileName){
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configFileName);
			PROPERTIES.load(inputStream);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * получние параметра по имени
	 * 
	 * @param propertyName
	 * @return String
	 * @throws TodoException
	 */
	public String getProperty(String propertyName) throws TodoException{
		String property = PROPERTIES.getProperty(propertyName);
		if (property == null){
			throw new TodoException(TodoExceptionCode.UNKNOWN_PROPERTY_EXCEPTION, propertyName);
		}
		return property;
	}

}
