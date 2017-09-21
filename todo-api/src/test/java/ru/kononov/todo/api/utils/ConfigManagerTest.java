package ru.kononov.todo.api.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ru.kononov.todo.api.exceptions.TodoException;

public class ConfigManagerTest {
	
	ConfigManager manager;
	
	@Before
	public void init(){
		manager = new ConfigManager("config_test.properties");
	}

	@Test
	public void testGetProperty() throws TodoException {
		String dbName = "tododo";
		String dbNameFromProperties = manager.getProperty(ConfigManager.MONGO_DB_NAME_PARAM_NAME);
		assertEquals(dbName, dbNameFromProperties);
	}
	
	@Test(expected = TodoException.class)
	public void testGetPropertyExceptionUnknownProperty() throws TodoException {
		manager.getProperty("unknown");
	}

}
