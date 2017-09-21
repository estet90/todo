package ru.kononov.todo.api.endpoints.websocket;

import javax.ejb.EJB;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.core.JsonProcessingException;

import ru.kononov.todo.api.endpoints.websocket.handlers.TaskEndpointHandler;
import ru.kononov.todo.api.exceptions.TodoException;

@ServerEndpoint("/tasks")
public class TaskEndpoint extends BaseEndpoint {

	@EJB
	private TaskEndpointHandler taskEndpointHandler;
	
	@Override
	@OnOpen
	public void onOpen(Session session, EndpointConfig endpointConfig) throws JsonProcessingException, TodoException {
		initHttpSession(endpointConfig);
		super.onOpen(session, endpointConfig); 
		String response = taskEndpointHandler.onOpen();
		broadcast(response);
	}

	@Override
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
	}

	@Override
	@OnMessage
	public void handleMessage(String message, Session session){
		LOGGER.info("onMessage!");
	}

//	@OnMessage
//	public void handleMessage(String message, Session session) {
//		Task task = new Task();
//		String id = httpSession.getAttribute("task.id").toString();
//		String name = httpSession.getAttribute("task.name").toString();
//		String description = httpSession.getAttribute("task.description").toString();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//		LocalDateTime datePlan = LocalDateTime.parse(httpSession.getAttribute("task.datePlan").toString(), formatter);
//		
//		if (id != null){
//			task.setId(id);
//		}
//		if (name != null){
//			task.setName(name);
//		}
//		if (description != null){
//			task.setDescription(description);
//		}
//		if (datePlan != null){
//			task.setDatePlan(datePlan);
//		}
//		
//		switch (message) {
//		case "create":
//			
//			break;
//		case "update":
//
//			break;
//		default:
//			break;
//		}
//	}

}
