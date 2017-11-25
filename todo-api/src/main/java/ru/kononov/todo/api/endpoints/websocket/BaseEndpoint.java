package ru.kononov.todo.api.endpoints.websocket;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;

import ru.kononov.todo.api.exceptions.TodoException;

/**
 * ������� ����� ��� ����������
 * 
 * @author admin
 *
 */
public class BaseEndpoint {

	protected static final Logger LOGGER = LogManager.getLogger(BaseEndpoint.class);
	
	protected Session session;
	protected HttpSession httpSession; 
	protected static Set<BaseEndpoint> endpoints = new CopyOnWriteArraySet<>();
	
	/**
	 * �������� �������� �����
	 * 
	 * @param session
	 * @param endpointConfig
	 * @throws JsonProcessingException
	 * @throws TodoException
	 */
	@OnOpen
	public void onOpen(Session session, EndpointConfig endpointConfig) throws JsonProcessingException, TodoException {
		initHttpSession(endpointConfig);
	}

	/**
	 * �������� �������� �����
	 * 
	 * @param session
	 * @param closeReason
	 */
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		LOGGER.info("onClose!");
	}

	/**
	 * ����������� ��������
	 * 
	 * @param message
	 * @param session
	 */
	@OnMessage
	public void handleMessage(String message, Session session){
		LOGGER.info("onMessage!");
	}
	
	/**
	 * ��������� ������
	 * 
	 * @param session
	 * @param throwable
	 */
	@OnError
	public void onError(Session session, Throwable throwable) {
		LOGGER.error(throwable);
	}

	/**
	 * �������� ������
	 * 
	 * @param message
	 */
	protected static void broadcast(String message) {
		endpoints.forEach(endpoint -> {
			synchronized (endpoint) {
				try {
					endpoint.session.getBasicRemote().sendText(message);
				} catch (IOException e) {
					LOGGER.error(e);
				}
			}
		});
	}
	
	/**
	 * ������������� ������
	 * 
	 * @param endpointConfig
	 */
	protected void initHttpSession(EndpointConfig endpointConfig){
		httpSession = (HttpSession) endpointConfig.getUserProperties()
                .get(HttpSession.class.getName());
	}

}
