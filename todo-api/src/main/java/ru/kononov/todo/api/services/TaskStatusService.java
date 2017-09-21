package ru.kononov.todo.api.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ru.kononov.todo.api.entities.TaskStatus;
import ru.kononov.todo.api.exceptions.TodoException;
import ru.kononov.todo.api.persistence.TaskStatusBean;

@Stateless
public class TaskStatusService {

	@EJB
	private TaskStatusBean taskStatusBean;
	
	public void create(TaskStatus status) throws TodoException {
		taskStatusBean.create(status);
	}
	
	public TaskStatus selectOne(String id) throws TodoException {
		return taskStatusBean.selectOne(id);
	}
	
	public TaskStatus selectByCode(String code) throws TodoException {
		TaskStatus status = new TaskStatus(code);
		return taskStatusBean.filter(status, null).get(0);
	}
	
	public List<TaskStatus> selectAll() throws TodoException {
		return taskStatusBean.selectAll();
	}
	
}
