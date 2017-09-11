package ru.kononov.todo.api.entities.convertors;

import ru.kononov.todo.api.entities.TaskStatus;

@Deprecated
public class TaskStatusToDocumentConvertor extends BaseToEntityDocumentConvertor<TaskStatus>{

	public TaskStatusToDocumentConvertor() {
		super();
	}
	
	@Override
	public Class<TaskStatus> getClassName() {
		return TaskStatus.class;
	}

}
