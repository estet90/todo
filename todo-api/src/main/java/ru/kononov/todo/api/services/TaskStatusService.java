package ru.kononov.todo.api.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ru.kononov.todo.api.entities.TaskStatus;
import ru.kononov.todo.api.exceptions.TodoException;
import ru.kononov.todo.api.persistence.TaskStatusBean;

/**
 * ��������� ����� ��� ������ � ��������� TaskStatus
 * 
 * @author admin
 *
 */
@Stateless
public class TaskStatusService {

	@EJB
	private TaskStatusBean taskStatusBean;
	
	/**
	 * ������� ����� ������
	 * 
	 * @param status
	 * @throws TodoException
	 */
	public void create(TaskStatus status) throws TodoException {
		taskStatusBean.create(status);
	}
	
	/**
	 * ������� ������ �� id
	 * 
	 * @param id
	 * @return
	 * @throws TodoException
	 */
	public TaskStatus selectOne(String id) throws TodoException {
		return taskStatusBean.selectOne(id);
	}
	
	/**
	 * ������� ������ �� ����
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
	 * ������� ��� �������
	 * 
	 * @return
	 * @throws TodoException
	 */
	public List<TaskStatus> selectAll() throws TodoException {
		return taskStatusBean.selectAll();
	}
	
}
