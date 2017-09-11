package ru.kononov.todo.api.entities;

import java.time.LocalDateTime;

import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.codecs.configuration.CodecRegistry;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Task extends BaseEntity{
	
	private TaskStatus status;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private LocalDateTime dateCreate;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private LocalDateTime dateModify;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private LocalDateTime dateComplete;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private LocalDateTime datePlan;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private LocalDateTime dateReminder;
	
	@Override
	public <T> BsonDocument toBsonDocument(Class<T> documentClass, CodecRegistry codecRegistry) {
		if (documentClass == Task.class) {
            return new BsonDocumentWrapper<Task> (this, codecRegistry.get(Task.class));
        }
		return null;
	}
	
	public TaskStatus getStatus() {
		return status;
	}
	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	public LocalDateTime getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(LocalDateTime dateCreate) {
		this.dateCreate = dateCreate;
	}
	public LocalDateTime getDateModify() {
		return dateModify;
	}
	public void setDateModify(LocalDateTime dateModify) {
		this.dateModify = dateModify;
	}
	public LocalDateTime getDateComplete() {
		return dateComplete;
	}
	public void setDateComplete(LocalDateTime dateComplete) {
		this.dateComplete = dateComplete;
	}
	public LocalDateTime getDatePlan() {
		return datePlan;
	}
	public void setDatePlan(LocalDateTime datePlan) {
		this.datePlan = datePlan;
	}
	public LocalDateTime getDateReminder() {
		return dateReminder;
	}
	public void setDateReminder(LocalDateTime dateReminder) {
		this.dateReminder = dateReminder;
	}

}
