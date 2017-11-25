package ru.kononov.todo.api.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ru.kononov.todo.api.entities.TaskStatus;
import ru.kononov.todo.api.exceptions.TodoException;
import ru.kononov.todo.api.persistence.TaskStatusBean;

/**
 * сервисный класс для работы с сущностью TaskStatus
 * 
 * @author admin
 *
 */
@Stateless
public class TaskStatusService {

	@EJB
	private TaskStatusBean taskStatusBean;
	
	/**
	 * создать новый статус
	 * 
	 * @param status
	 * @throws TodoException
	 */
	public void create(TaskStatus status) throws TodoException {
		taskStatusBean.create(status);
	}
	
	/**
	 * выбрать статус по id
	 * 
	 * @param id
	 * @return
	 * @throws TodoException
	 */
	public TaskStatus selectOne(String id) throws TodoException {
		return taskStatusBean.selectOne(id);
	}
	
	/**
	 * выбрать статус по коду
	 * 
	 * @param code
	 * @return
	 * @throws TodoException
	 */
	public TaskStatus selectByCode(String code) throws TodoException {
		TaskStatus status = new TaskStatus(code);
		return taskStatusBean.filter(status, null).get(0);
	}
	
	/**
	 * выбрать все статусы
	 * 
	 * @return
	 * @throws TodoException
	 */
	public List<TaskStatus> selectAll() throws TodoException {
		return taskStatusBean.selectAll();
	}
	
}
