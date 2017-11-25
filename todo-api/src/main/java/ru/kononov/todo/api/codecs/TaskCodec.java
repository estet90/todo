package ru.kononov.todo.api.codecs;

import java.time.LocalDateTime;

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;

import ru.kononov.todo.api.entities.Task;
import ru.kononov.todo.api.entities.TaskStatus;

/**
 * Task <-> Bson
 * 
 * @author admin
 *
 */
public class TaskCodec implements Codec<Task> {

	private CodecRegistry codecRegistry;

	/**
	 * конструктор по умолчанию, принимающий на вход реестр кодеков
	 * 
	 * @param codecRegistry
	 */
	public TaskCodec(CodecRegistry codecRegistry) {
		this.codecRegistry = codecRegistry;
	}

	@Override
	public Class<Task> getEncoderClass() {
		return Task.class;
	}

	/**
	 * Task -> Bson
	 */
	@Override
	public void encode(BsonWriter writer, Task task, EncoderContext encoderContext) {

		writer.writeStartDocument();

		if (task.getId() != null) {
			writer.writeName("_id");
			writer.writeObjectId(new ObjectId(task.getId()));
		}
		if (task.getName() != null) {
			writer.writeName("name");
			writer.writeString(task.getName());
		}
		if (task.getDescription() != null) {
			writer.writeName("description");
			writer.writeString(task.getDescription());
		}

		Codec<TaskStatus> statusTimeCodec = codecRegistry.get(TaskStatus.class);
		if (task.getStatus() != null) {
			writer.writeName("status");
			encoderContext.encodeWithChildContext(statusTimeCodec, writer, task.getStatus());
		}

		Codec<LocalDateTime> dateTimeCodec = codecRegistry.get(LocalDateTime.class);
		if (task.getDateComplete() != null) {
			writer.writeName("dateComplete");
			encoderContext.encodeWithChildContext(dateTimeCodec, writer, task.getDateComplete());
		}
		if (task.getDateCreate() != null) {
			writer.writeName("dateCreate");
			encoderContext.encodeWithChildContext(dateTimeCodec, writer, task.getDateCreate());
		}
		if (task.getDateModify() != null) {
			writer.writeName("dateModify");
			encoderContext.encodeWithChildContext(dateTimeCodec, writer, task.getDateModify());
		}
		if (task.getDatePlan() != null) {
			writer.writeName("datePlan");
			encoderContext.encodeWithChildContext(dateTimeCodec, writer, task.getDatePlan());
		}
		if (task.getDateReminder() != null) {
			writer.writeName("dateReminder");
			encoderContext.encodeWithChildContext(dateTimeCodec, writer, task.getDateReminder());
		}

		writer.writeEndDocument();
	}

	/**
	 * Bson -> Task
	 * @return Task
	 */
	@Override
	public Task decode(BsonReader reader, DecoderContext decoderContext) {
		Task task = new Task();
		Codec<TaskStatus> statusCodec = codecRegistry.get(TaskStatus.class);
		Codec<LocalDateTime> dateTimeCodec = codecRegistry.get(LocalDateTime.class);

		reader.readStartDocument();
		while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
			reader.readName();
			switch (reader.getCurrentName()) {
			case "_id":
				task.setId(reader.readObjectId().toHexString());
				break;
			case "name":
				task.setName(reader.readString());
				break;
			case "description":
				task.setDescription(reader.readString());
				break;
			case "status":
				TaskStatus status = statusCodec.decode(reader, decoderContext);
				task.setStatus(status);
				break;
			case "dateComplete":
				LocalDateTime dateComplete = dateTimeCodec.decode(reader, decoderContext);
				task.setDateComplete(dateComplete);
				break;
			case "dateCreate":
				LocalDateTime dateCreate = dateTimeCodec.decode(reader, decoderContext);
				task.setDateComplete(dateCreate);
				break;
			case "dateModify":
				LocalDateTime dateModify = dateTimeCodec.decode(reader, decoderContext);
				task.setDateModify(dateModify);
				break;
			case "datePlan":
				LocalDateTime datePlan = dateTimeCodec.decode(reader, decoderContext);
				task.setDatePlan(datePlan);
				break;
			case "dateReminder":
				LocalDateTime dateReminder = dateTimeCodec.decode(reader, decoderContext);
				task.setDateReminder(dateReminder);
				break;
			default:
				break;
			}
		}
		reader.readEndDocument();

		return task;
	}

}
