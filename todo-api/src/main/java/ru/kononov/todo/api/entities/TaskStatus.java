package ru.kononov.todo.api.entities;

import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.codecs.configuration.CodecRegistry;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * статус задачи
 * 
 * @author admin
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskStatus extends BaseEntity {

	private String code;

	public TaskStatus() {
	}

	public TaskStatus(String code) {
		this.code = code;
	}

	@Override
	public <T> BsonDocument toBsonDocument(Class<T> documentClass, CodecRegistry codecRegistry) {
		if (documentClass == TaskStatus.class) {
			return new BsonDocumentWrapper<TaskStatus>(this, codecRegistry.get(TaskStatus.class));
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskStatus other = (TaskStatus) obj;
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
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	public static TaskStatus createStatusWithId(TaskStatus status){
		TaskStatus newStatus = new TaskStatus();
		newStatus.setId(status.getId());
		return newStatus;
	}
	
	/**
	 * возможные статусы
	 * 
	 * @author admin
	 *
	 */
	public enum TaskStatusCode {
		NEW, COMPLETE, OVERDUE;
	}

}
