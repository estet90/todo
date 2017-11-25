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

import ru.kononov.todo.api.endpoints.rest.handlers.TaskControllerHandler;
import ru.kononov.todo.api.entities.Task;

/**
 * REST-котроллер для сущности задача
 * 
 * @author admin
 *
 */
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v1/tasks")
public class TaskController {
	
	@EJB
	private TaskControllerHandler taskControllerHandler;
	
	/**
	 * получение задачи по id
	 * 
	 * @param id
	 * @return Response
	 */
	@GET
	@Path("/{id}")
	public Response selectOne(@PathParam("id") String id)  {
		return taskControllerHandler.selectOne(id);
	}
	
	/**
	 * получение списка всех задач
	 * 
	 * @return Response
	 */
	@GET
	public Response selectAll()  {
		return taskControllerHandler.selectAll();
	}
	
	/**
	 * создание задачи
	 * 
	 * @param task
	 * @return Response
	 */
	@POST
	@Path("/")
	public Response create(Task task)  {
		return taskControllerHandler.create(task);
	}

}
