package ru.kononov.todo.api.endpoints.websocket;

import javax.ejb.EJB;
import javax.websocket.EndpointConfig;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.core.JsonProcessingException;

import ru.kononov.todo.api.endpoints.websocket.handlers.TaskEndpointHandler;
import ru.kononov.todo.api.exceptions.TodoException;

/**
 * конечная точка для работы с сущностью задача
 * 
 * @author admin
 *
 */
@ServerEndpoint("/tasks")
public class TaskEndpoint extends BaseEndpoint {

	@EJB
	private TaskEndpointHandler taskEndpointHandler;
	
	/**
	 * получение списка всех задач
	 */
	@Override
	@OnOpen
	public void onOpen(Session session, EndpointConfig endpointConfig) throws JsonProcessingException, TodoException {
		initHttpSession(endpointConfig);
		super.onOpen(session, endpointConfig); 
		String response = taskEndpointHandler.onOpen();
		broadcast(response);
	}

}
