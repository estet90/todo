package ru.kononov.todo.api.endpoints.rest.handlers;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.kononov.todo.api.entities.Task;
import ru.kononov.todo.api.entities.TaskStatus;
import ru.kononov.todo.api.exceptions.TodoException;
import ru.kononov.todo.api.services.TaskService;
import ru.kononov.todo.api.services.TaskStatusService;

/**
 * ���������� ��� REST-���������� ��� ������ � ��������� ������ ������
 * 
 * @author admin
 *
 */
public class TaskStatusControllerHandler {
	
	private static final Logger LOGGER = LogManager.getLogger(TaskStatusControllerHandler.class); 

	@EJB
	private TaskService taskService;
	@EJB
	private TaskStatusService taskStatusService;

	/**
	 * ��������� ����� ���� ��������
	 * 
	 * @return Response
	 */
	public Response selectAll() {
		List<TaskStatus> statuses = null;
		try {
			statuses = taskStatusService.selectAll();
		} catch (Exception e) {
			LOGGER.error(e);
			Response.serverError().build();
		}
		return Response.ok(statuses).build();
	}
	
	/**
	 * �������� ������ �������
	 * ������ ������ ��� ������������
	 * 
	 * @param status
	 * @return Response
	 */
	public Response create(TaskStatus status) {
		try {
			taskStatusService.create(status);
			status = taskStatusService.selectByCode(status.getCode());
		} catch (Exception e) {
			LOGGER.error(e);
			Response.serverError().build();
		}
		return Response.ok(status).status(Status.CREATED).build();
	}
	
	/**
	 * ��������� ������� �� id
	 * 
	 * @param id
	 * @return Response
	 */
	public Response selectOne(String id) {
		TaskStatus status = null;
		try {
			status = taskStatusService.selectOne(id);
		} catch (TodoException e) {
			LOGGER.error(e);
			Response.serverError().build();
		}
		return Response.ok(status).build();
	}
	
	/**
	 * ��������� ����� �� id �������
	 * 
	 * @param id
	 * @return Response
	 */
	public Response selectTasks(String id) {
		List<Task> tasks = null;
		try {
			tasks = taskService.selectByStatusId(id);
		} catch (TodoException e) {
			LOGGER.error(e);
			Response.serverError().build();
		}
		return Response.ok(tasks).build();
	}
}
