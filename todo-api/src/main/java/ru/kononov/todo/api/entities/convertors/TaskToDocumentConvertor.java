package ru.kononov.todo.api.entities.convertors;

import ru.kononov.todo.api.entities.Task;

@Deprecated
public class TaskToDocumentConvertor extends BaseToEntityDocumentConvertor<Task> {

	public TaskToDocumentConvertor() {
		super();
	}
	
	@Override
	public Class<Task> getClassName() {
		return Task.class;
	}

}
