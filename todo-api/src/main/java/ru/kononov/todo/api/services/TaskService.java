package ru.kononov.todo.api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ru.kononov.todo.api.entities.Task;
import ru.kononov.todo.api.entities.TaskStatus;
import ru.kononov.todo.api.entities.TaskStatus.TaskStatusCode;
import ru.kononov.todo.api.exceptions.TodoException;
import ru.kononov.todo.api.persistence.TaskBean;
import ru.kononov.todo.api.persistence.TaskStatusBean;

/**
 * сервисный класс для работы с сущностью Task
 * 
 * @author admin
 *
 */
@Stateless
public class TaskService {

	@EJB
	private TaskBean taskBean;
	@EJB
	private TaskStatusBean taskStatusBean;

	/**
	 * создать новую задачу
	 * 
	 * @param task
	 * @throws TodoException
	 */
	public void create(Task task) throws TodoException {
		TaskStatus statusForFilter = taskStatusBean.filter(new TaskStatus(TaskStatusCode.NEW.name()), null).get(0);
		task.setStatus(TaskStatus.createStatusWithId(statusForFilter));
		task.setDateCreate(LocalDateTime.now());
		task.setDateModify(LocalDateTime.now());
		taskBean.create(task);
	}

	/**
	 * выбрать одну задачу по id
	 * 
	 * @param id
	 * @return Task
	 * @throws TodoException
	 */
	public Task selectOne(String id) throws TodoException {
		return taskBean.selectOne(id);
	}

	/**
	 * выбрать все задачи
	 * 
	 * @return List<Task>
	 * @throws TodoException
	 */
	public List<Task> selectAll() throws TodoException {
		return taskBean.selectAll();
	}

	/**
	 * выбрать задачи по коду статуса
	 * 
	 * @param statusCode
	 * @return List<Task>
	 * @throws TodoException
	 */
	public List<Task> selectByStatusCode(String statusCode) throws TodoException {
		TaskStatus statusForFilter = taskStatusBean.filter(new TaskStatus(statusCode), null).get(0);
		Task task = new Task();
		task.setStatus(TaskStatus.createStatusWithId(statusForFilter));
		return taskBean.filter(task, null);
	}

	/**
	 * выбрать задачи по id статуса
	 * 
	 * @param statusId
	 * @return List<Task>
	 * @throws TodoException
	 */
	public List<Task> selectByStatusId(String statusId) throws TodoException {
		TaskStatus status = new TaskStatus();
		status.setId(statusId);
		Task task = new Task();
		task.setStatus(status);
		return taskBean.filter(task, null);
	}

	/**
	 * обновить одну задачу
	 * 
	 * @param task
	 * @return Task
	 * @throws TodoException
	 */
	public Task update(Task task) throws TodoException {
		task.setDateModify(LocalDateTime.now());
		return taskBean.update(task);
	}

	/**
	 * удалить одну задачу
	 * 
	 * @param task
	 * @throws TodoException
	 */
	public void delete(Task task) throws TodoException {
		taskBean.delete(task);
	}
	
	/**
	 * поиск по задачам
	 * 
	 * @param task
	 * @param conditions
	 * @return List<Task>
	 * @throws TodoException
	 */
	public List<Task> filter(Task task, Map<String, Object> conditions) throws TodoException{
		return taskBean.filter(task, conditions);
	}

}
