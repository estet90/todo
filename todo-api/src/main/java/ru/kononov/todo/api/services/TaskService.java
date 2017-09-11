package ru.kononov.todo.api.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ru.kononov.todo.api.entities.Task;
import ru.kononov.todo.api.entities.TaskStatus;
import ru.kononov.todo.api.entities.TaskStatus.TaskStatusCode;
import ru.kononov.todo.api.entities.exceptions.TodoException;
import ru.kononov.todo.api.persistence.TaskBean;
import ru.kononov.todo.api.persistence.TaskStatusBean;

@Stateless
public class TaskService {

	@EJB
	private TaskBean taskBean;
	@EJB
	private TaskStatusBean taskStatusBean;

	public void create(Task task) throws TodoException {
		TaskStatus statusForFilter = taskStatusBean.getStatusByCode(TaskStatusCode.NEW.name());
		task.setStatus(TaskStatus.createStatusWithId(statusForFilter));
		task.setDateCreate(LocalDateTime.now());
		task.setDateModify(LocalDateTime.now());
		taskBean.create(task);
	}

	public Task selectOne(String id) throws TodoException {
		return taskBean.selectOne(id);
	}

	public List<Task> selectAll() throws TodoException {
		return taskBean.selectAll();
	}

	public List<Task> selectByStatusCode(String statusCode) throws TodoException {
		TaskStatus statusForFilter = taskStatusBean.getStatusByCode(statusCode);
		Task task = new Task();
		task.setStatus(TaskStatus.createStatusWithId(statusForFilter));
		return taskBean.filter(task);
	}

	public List<Task> selectByStatusId(String statusId) throws TodoException {
		TaskStatus status = new TaskStatus();
		status.setId(statusId);
		Task task = new Task();
		task.setStatus(status);
		return taskBean.filter(task);
	}

	public Task update(Task task) throws InstantiationException, TodoException {
		task.setDateModify(LocalDateTime.now());
		return taskBean.update(task);
	}

	public void delete(Task task) throws TodoException {
		taskBean.delete(task);
	}

}
