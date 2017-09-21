package ru.kononov.todo.api.endpoints.websocket.handlers.utils;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;

public class JacksonWrapper<T> {

	private static final Logger LOGGER = LogManager.getLogger(JacksonWrapper.class);

	private ObjectMapper mapper;
	private FilterProvider filterProvider;

	/**
	 * 
	 */
	public JacksonWrapper(){
		this.mapper = new ObjectMapper();
	}
	
	/**
	 * 
	 * @param filterProvider {@link FilterProvider} contains ignorable fields
	 */
	public JacksonWrapper(FilterProvider filterProvider) {
		this.mapper = new ObjectMapper();
		this.filterProvider = filterProvider;
	}

	/**
	 * Object -> String
	 * 
	 * @param object {@link java.lang.invoke.MethodHandleImpl.BindCaller.T} POJO
	 * @return {@link String} serializedObject
	 * @throws JsonProcessingException when object is incorrect
	 */
	public String getSerializedObject(T object) throws JsonProcessingException {
		String serializedObject = filterProvider != null ? 
				mapper.writer(filterProvider).writeValueAsString(object)
				: mapper.writeValueAsString(object);
		LOGGER.debug("serialized {}:\n {}", object.getClass(), serializedObject);
		return serializedObject;
	}

	/**
	 * String -> Object
	 * 
	 * @param objectStr {@link String} object
	 * @param clazz {@link Class} target class
	 * @return {@link java.lang.invoke.MethodHandleImpl.BindCaller.T} deserialized object
	 * @throws IOException when readValue
	 */
	public T getDeserializedObject(String objectStr, Class<T> clazz) throws IOException {
		T object = mapper.readValue(objectStr, clazz);
		LOGGER.debug("deserialize \n{} \nto {}", objectStr, clazz);
		return object;
	}
}