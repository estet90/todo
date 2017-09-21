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
import ru.kononov.todo.api.entities.TaskStatus;
import ru.kononov.todo.api.exceptions.TodoException;
import ru.kononov.todo.api.services.TaskService;
import ru.kononov.todo.api.services.TaskStatusService;

@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v1/taskStatuses")
public class TaskStatusController {

	@EJB
	private TaskService taskService;
	@EJB
	private TaskStatusService taskStatusService;

	@GET
	public Response selectAll() throws TodoException{
		List<TaskStatus> statuses = taskStatusService.selectAll();
		return Response.ok(statuses).status(Status.OK).build();
	}
	
	@POST
	public Response create(TaskStatus status) throws TodoException{
		taskStatusService.create(status);
		status = taskStatusService.selectByCode(status.getCode());
		return Response.ok(status).status(Status.OK).build();
	}
	
	@GET
	@Path("/{id}")
	public Response selectOne(@PathParam("id") String id) throws TodoException{
		TaskStatus status = taskStatusService.selectOne(id);
		return Response.ok(status).status(Status.OK).build();
	}
	
	@GET
	@Path("/{id}/tasks")
	public Response selectTasks(@PathParam("id") String id) throws TodoException{
		List<Task> tasks = taskService.selectByStatusId(id);
		return Response.ok(tasks).status(Status.OK).build();
	}
	
}
