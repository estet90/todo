package ru.kononov.todo.api.exceptions;

public class TodoException extends Exception {

	private static final long serialVersionUID = 1L;

	private TodoExceptionCode code;
	
	public TodoException(TodoExceptionCode code, Throwable cause, Object... params) {
		super(code.descriptionOf(params), cause);
		this.code = code;
	}
	
	public TodoException(TodoExceptionCode code, Object... params){
		super(code.descriptionOf(params));
		this.code = code;
	}

	public TodoExceptionCode getCode() {
		return code;
	}
}
