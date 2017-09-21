package ru.kononov.todo.api.entities;

import org.bson.conversions.Bson;

public abstract class BaseEntity implements Bson{

	protected String id;
	
	protected String name;
	
	protected String description;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
