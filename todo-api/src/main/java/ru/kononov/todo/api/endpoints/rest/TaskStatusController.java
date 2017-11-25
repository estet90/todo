package ru.kononov.todo.api.endpoints.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ru.kononov.todo.api.endpoints.rest.handlers.TaskStatusControllerHandler;
import ru.kononov.todo.api.entities.TaskStatus;

/**
 * REST-котроллер для сущности статус задачи
 * 
 * @author admin
 *
 */
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v1/taskStatuses")
public class TaskStatusController {

	@EJB
	private TaskStatusControllerHandler taskStatusControllerHandler;

	/**
	 * получение спика всех статусов
	 * 
	 * @return Response
	 */
	@GET
	public Response selectAll() {
		return taskStatusControllerHandler.selectAll();
	}
	
	/**
	 * создание нового статуса
	 * сервис сделан для тестирования
	 * 
	 * @param status
	 * @return Response
	 */
	@POST
	public Response create(TaskStatus status) {
		return taskStatusControllerHandler.create(status);
	}
	
	/**
	 * получение статуса по id
	 * 
	 * @param id
	 * @return Response
	 */
	@GET
	@Path("/{id}")
	public Response selectOne(@PathParam("id") String id) {
		return taskStatusControllerHandler.selectOne(id);
	}
	
	/**
	 * получение задач по id статуса
	 * 
	 * @param id
	 * @return Response
	 */
	@GET
	@Path("/{id}/tasks")
	public Response selectTasks(@PathParam("id") String id) {
		return taskStatusControllerHandler.selectTasks(id);
	}
	
}
