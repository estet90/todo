package ru.kononov.todo.api.persistence;

import javax.ejb.Stateless;

import ru.kononov.todo.api.entities.Task;

/**
 * класс для работы с коллекцией Task
 * 
 * @author admin
 *
 */
@Stateless
public class TaskBean extends BaseEntityBean<Task>{

	@Override
	protected Class<Task> getClassName() {
		return Task.class;
	}
}
