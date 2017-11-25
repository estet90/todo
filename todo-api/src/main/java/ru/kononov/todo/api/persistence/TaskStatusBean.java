package ru.kononov.todo.api.persistence;

import javax.ejb.Stateless;

import ru.kononov.todo.api.entities.TaskStatus;

/**
 * класс для работы с коллекцией TaskStatus
 * 
 * @author admin
 *
 */
@Stateless
public class TaskStatusBean extends BaseEntityBean<TaskStatus>{

	@Override
	protected Class<TaskStatus> getClassName() {
		return TaskStatus.class;
	}
	
}
