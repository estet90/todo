package ru.kononov.todo.api.endpoints.websocket;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/tasks")
public class TaskEndpoint {

	@OnOpen
	public void onOpen(Session session, EndpointConfig endpointConfig) {
		// TODO Auto-generated method stub
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		// TODO Auto-generated method stub
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		// TODO Auto-generated method stub
	}

	@OnMessage
	public void handleMessage(String message, Session session) {
	}

}
