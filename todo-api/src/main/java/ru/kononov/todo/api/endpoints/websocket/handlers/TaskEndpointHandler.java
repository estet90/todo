package ru.kononov.todo.api.endpoints.websocket.handlers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.fasterxml.jackson.core.JsonProcessingException;

import ru.kononov.todo.api.endpoints.websocket.handlers.utils.JacksonWrapper;
import ru.kononov.todo.api.entities.Task;
import ru.kononov.todo.api.exceptions.TodoException;
import ru.kononov.todo.api.services.TaskService;

@Stateless
public class TaskEndpointHandler {
	
	@EJB
	private TaskService taskService;
	
	public String onOpen() throws JsonProcessingException, TodoException {
		JacksonWrapper<List<Task>> tasksWrapper = new JacksonWrapper<>();
		List<Task> tasks = taskService.selectAll();
		String repsonse = tasksWrapper.getSerializedObject(tasks);
		return repsonse;
	}
	
//	public void handleMessage(String message, HttpSession session) throws TodoException {
//		Task task = new Task();
//		String id = session.getAttribute("task.id").toString();
//		String name = session.getAttribute("task.name").toString();
//		String description = session.getAttribute("task.description").toString();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//		LocalDateTime datePlan = LocalDateTime.parse(session.getAttribute("task.datePlan").toString(), formatter);
//		
//		List<String> namesEmptyMandatoryAttributes = new ArrayList<>(0);
//		
//		if (id != null){
//			task.setId(id);
//		}
//		if (name != null){
//			task.setName(name);
//		} else {
//			namesEmptyMandatoryAttributes.add("name");
//		}
//		if (description != null){
//			task.setDescription(description);
//		}
//		if (datePlan != null){
//			task.setDatePlan(datePlan);
//		} else {
//			namesEmptyMandatoryAttributes.add("datePlan");
//		}
//		
//		if (namesEmptyMandatoryAttributes.size() > 0){
//			throw new TodoException(TodoExceptionCode.REQUEST_PARAMETERS_EXCEPTION, "handleMessage", namesEmptyMandatoryAttributes);
//		}
//		
//		switch (message) {
//		case "create":
//			service.create(task);
//			break;
//		case "update":
//			service.update(task);	
//			break;
//		default:
//			break;
//		}
//	}

}
