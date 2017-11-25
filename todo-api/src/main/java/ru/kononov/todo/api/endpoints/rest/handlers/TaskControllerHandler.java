package ru.kononov.todo.api.endpoints.rest.handlers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.kononov.todo.api.entities.Task;
import ru.kononov.todo.api.services.TaskService;
import ru.kononov.todo.api.services.TaskStatusService;

/**
 * обработчик для REST-котроллера для работы с сущностью задача
 * 
 * @author admin
 *
 */
@Stateless
public class TaskControllerHandler {
	
	private static final Logger LOGGER = LogManager.getLogger(TaskControllerHandler.class); 

	@EJB
	private TaskService taskService;
	@EJB
	private TaskStatusService taskStatusService;
	
	/**
	 * получение задачи по id
	 * 
	 * @param id
	 * @return Response
	 * @throws TodoException
	 */
	public Response selectOne(String id) {
		Task task = null;
		try {
			task = taskService.selectOne(id);
		} catch (Exception e) {
			LOGGER.error(e);
			Response.serverError().build();
		}
		return Response.ok(task).build();
	}
	
	/**
	 * получение списка всех задач
	 * 
	 * @return Response
	 * @throws TodoException
	 */
	public Response selectAll() {
		List<Task> tasks = null;
		try {
			tasks = taskService.selectAll();
		} catch (Exception e) {
			LOGGER.error(e);
			Response.serverError().build();
		}
		return Response.ok(tasks).build();
	}
	
	/**
	 * создание задачи
	 * 
	 * @param task
	 * @return Response
	 * @throws TodoException
	 */
	public Response create(Task task){
		try {
			taskService.create(task);
		} catch (Exception e) {
			LOGGER.error(e);
			Response.serverError().build();
		}
		return Response.ok(task).status(Status.CREATED).build();
	}
	
}
