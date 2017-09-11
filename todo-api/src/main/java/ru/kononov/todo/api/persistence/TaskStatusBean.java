package ru.kononov.todo.api.persistence;

import javax.ejb.Stateless;

import ru.kononov.todo.api.entities.TaskStatus;
import ru.kononov.todo.api.entities.exceptions.TodoException;

@Stateless
public class TaskStatusBean extends BaseEntityBean<TaskStatus>{

	@Override
	protected Class<TaskStatus> getClassName() {
		return TaskStatus.class;
	}

	@Override
	protected String getCollectionName() {
		return "TaskStatuses";
	}
	
	public TaskStatus getStatusByCode(String code) throws TodoException {
		return filter(new TaskStatus(code)).get(0);
	}
	
}
