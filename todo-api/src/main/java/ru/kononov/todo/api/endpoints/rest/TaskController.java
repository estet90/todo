package ru.kononov.todo.api.endpoints.rest;

import java.util.List;

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
import javax.ws.rs.core.Response.Status;

import ru.kononov.todo.api.entities.Task;
import ru.kononov.todo.api.exceptions.TodoException;
import ru.kononov.todo.api.services.TaskService;
import ru.kononov.todo.api.services.TaskStatusService;

@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v1/tasks")
public class TaskController {
	
	@EJB
	private TaskService taskService;
	@EJB
	private TaskStatusService taskStatusService;
	
	@GET
	@Path("/{id}")
	public Response selectOne(@PathParam("id") String id) throws TodoException {
		Task task = taskService.selectOne(id);
		return Response.ok(task).status(Status.OK).build();
	}
	
	@GET
	public Response selectAll() throws TodoException {
		List<Task> tasks = taskService.selectAll();
		return Response.ok(tasks).status(Status.OK).build();
	}
	
	@POST
	@Path("/")
	public Response create(Task task) throws TodoException {
		taskService.create(task);
		return Response.ok(task).status(Status.OK).build();
	}

}
