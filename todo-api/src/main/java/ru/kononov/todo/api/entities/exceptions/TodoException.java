package ru.kononov.todo.api.entities.exceptions;

//TODO разобраться с ошибками
public class TodoException extends Exception {

	private static final long serialVersionUID = 1L;

	public TodoException(TodoExceptionCode code, Throwable cause, Object... params) {
		super(code.descriptionOf(params), cause);
	}
	
	public TodoException(TodoExceptionCode code, Object... params){
		super(code.descriptionOf(params));
	}

}
