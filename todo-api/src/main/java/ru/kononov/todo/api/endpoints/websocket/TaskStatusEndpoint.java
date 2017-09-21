package ru.kononov.todo.api.endpoints.websocket;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import ru.kononov.todo.api.endpoints.websocket.handlers.utils.JacksonWrapper;
import ru.kononov.todo.api.entities.TaskStatus;
import ru.kononov.todo.api.exceptions.TodoException;
import ru.kononov.todo.api.services.TaskStatusService;

@Stateless
@ServerEndpoint("/statuses")
public class TaskStatusEndpoint extends BaseEndpoint{

	@EJB
	TaskStatusService service;

	@Override
	@OnOpen
	public void onOpen(Session session, EndpointConfig endpointConfig) {
		System.out.println("onOpen!");
		try {
			this.session = session;
			endpoints.add(this);
			List<TaskStatus> statuses = service.selectAll();
			String message = new JacksonWrapper<List<TaskStatus>>().getSerializedObject(statuses);
			broadcast(message);
		} catch (IOException | TodoException e) {
			e.printStackTrace();
		}
	}

	@Override
	@OnMessage
	public void handleMessage(String message, Session session) {
		System.out.println("onMessage override!!!");
	}

	
}
