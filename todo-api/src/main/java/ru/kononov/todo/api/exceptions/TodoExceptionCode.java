package ru.kononov.todo.api.exceptions;

public enum TodoExceptionCode {

	UNKNOWN_EXCEPTION(-1, "�������������� ������"),

	CONVERTING_EXCEPTION(-101, "������ ��� �������������� ���� {} � {}"), 
	SERIALIZE_EXCEPTION(-102,"������ ��� ������������/�������������� ���� {} � {}"), 
	UNKNOWN_PROPERTY_EXCEPTION(-103, "����������� ��������: {}"),
	REQUEST_PARAMETERS_EXCEPTION(-104, "��� ������� {} �� ���������� ������������ ���������: {}");

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
			description = description.replaceFirst("\\{\\}", param.toString());
		}
		return description;
	}

}
