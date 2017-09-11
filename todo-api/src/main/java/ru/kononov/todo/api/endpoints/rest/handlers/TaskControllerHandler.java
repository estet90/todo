package ru.kononov.todo.api.endpoints.rest.handlers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ru.kononov.todo.api.entities.Task;
import ru.kononov.todo.api.entities.exceptions.TodoException;
import ru.kononov.todo.api.services.TaskService;
import ru.kononov.todo.api.services.TaskStatusService;

@Stateless
public class TaskControllerHandler {

	@EJB
	private TaskService taskService;
	@EJB
	private TaskStatusService taskStatusService;
	
	public Response selectOne(String id) throws TodoException {
		Task task = taskService.selectOne(id);
		return Response.ok(task).status(Status.OK).build();
	}
	
	public Response selectAll() throws TodoException {
		List<Task> tasks = taskService.selectAll();
		return Response.ok(tasks).status(Status.OK).build();
	}
	
	public Response create(Task task) throws TodoException{
		taskService.create(task);
		return Response.ok(task).status(Status.OK).build();
	}
	
}
