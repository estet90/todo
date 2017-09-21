package ru.kononov.todo.api.endpoints.websocket.handlers.types;

import ru.kononov.todo.api.exceptions.TodoException;

public class Error {

	private int code;
	private String description;
	private String trace;
	
	public Error() {}
	
	public Error(int code, String description, String trace){
		this.code = code;
		this.description = description;
		this.trace = trace;
	}
	
	public Error(TodoException exception){
		this.code = exception.getCode().getId();
		this.description = exception.getMessage();
		this.trace = exception.getCause().getStackTrace().toString();
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTrace() {
		return trace;
	}

	public void setTrace(String trace) {
		this.trace = trace;
	}

}
