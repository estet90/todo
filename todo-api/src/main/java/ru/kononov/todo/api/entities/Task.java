package ru.kononov.todo.api.entities;

import java.time.LocalDateTime;

import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.codecs.configuration.CodecRegistry;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * задача
 * 
 * @author admin
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Task extends BaseEntity{
	
	private TaskStatus status;
	private LocalDateTime dateCreate;
	private LocalDateTime dateModify;
	private LocalDateTime dateComplete;
	private LocalDateTime datePlan;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (dateComplete == null) {
			if (other.dateComplete != null)
				return false;
		} else if (!dateComplete.equals(other.dateComplete))
			return false;
		if (dateCreate == null) {
			if (other.dateCreate != null)
				return false;
		} else if (!dateCreate.equals(other.dateCreate))
			return false;
		if (dateModify == null) {
			if (other.dateModify != null)
				return false;
		} else if (!dateModify.equals(other.dateModify))
			return false;
		if (datePlan == null) {
			if (other.datePlan != null)
				return false;
		} else if (!datePlan.equals(other.datePlan))
			return false;
		if (dateReminder == null) {
			if (other.dateReminder != null)
				return false;
		} else if (!dateReminder.equals(other.dateReminder))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
	

}
