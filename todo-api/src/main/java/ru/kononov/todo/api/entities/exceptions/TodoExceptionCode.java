package ru.kononov.todo.api.entities.exceptions;

public enum TodoExceptionCode {

	UNKNOWN_EXCEPTION(-1, "Необработанная ошибка"),

	CONVERTING_EXCEPTION(-101, "Ошибка при преобразовании типа {} в {}"), 
	SERIALIZE_EXCEPTION(-102,"Ошибка при сериализации/десериализации типа {} в {}"), 
	UNKNOWN_PROPERTY_EXCEPTION(-103, "Неизвестное своймтво: {}");

	private int id;
	private String description;

	private TodoExceptionCode(int code, String description) {
		this.id = code;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String descriptionOf(Object... params) {
		for (Object param : params){
			description.replaceFirst("{}", param.toString());
		}
		return description;
	}

}
