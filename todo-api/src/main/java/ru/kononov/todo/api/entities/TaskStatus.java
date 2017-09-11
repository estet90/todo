package ru.kononov.todo.api.entities;

import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.codecs.configuration.CodecRegistry;

import com.fasterxml.jackson.annotation.JsonInclude;

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

	public static TaskStatus createStatusWithId(TaskStatus status){
		TaskStatus newStatus = new TaskStatus();
		newStatus.setCode(status.getCode());
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
