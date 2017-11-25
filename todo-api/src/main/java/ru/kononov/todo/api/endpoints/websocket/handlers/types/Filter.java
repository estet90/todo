package ru.kononov.todo.api.endpoints.websocket.handlers.types;

import java.util.Map;

import ru.kononov.todo.api.entities.BaseEntity;

/**
 * поисковый фильтр для вебсокетов
 * 
 * @author admin
 *
 */
public class Filter {

	private BaseEntity entity;
	private Map<String, Object> conditions;

	public BaseEntity getEntity() {
		return entity;
	}

	public void setEntity(BaseEntity entity) {
		this.entity = entity;
	}

	public Map<String, Object> getConditions() {
		return conditions;
	}

	public void setConditions(Map<String, Object> conditions) {
		this.conditions = conditions;
	}

}
