package ru.kononov.todo.api.codecs;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;

import ru.kononov.todo.api.entities.TaskStatus;

public class TaskStatusCodec implements Codec<TaskStatus>{
	
	private Codec<Document> documentCodec;
	
	public TaskStatusCodec(final CodecRegistry codecRegistry) {
		this.documentCodec = new DocumentCodec(codecRegistry);
	}
	
	@Override
	public Class<TaskStatus> getEncoderClass() {
		return TaskStatus.class;
	}
	
	@Override
	public void encode(BsonWriter writer, TaskStatus status, EncoderContext encoderContext) {
		Document document = new Document();
		
		if (status.getId() != null) {
			document.put("_id", new ObjectId(status.getId()));
		}
		if (status.getName() != null) {
			document.put("name", status.getName());
		}
		if (status.getDescription() != null) {
			document.put("description", status.getDescription());
		}
		if (status.getCode() != null) {
			document.put("code", status.getCode());
		}
		
		documentCodec.encode(writer, document, encoderContext);
	}

	@Override
	public TaskStatus decode(BsonReader reader, DecoderContext decoderContext) {
		Document document = documentCodec.decode(reader, decoderContext);
		TaskStatus status = new TaskStatus();
		
		if (document.getObjectId("_id") != null){
			status.setId(document.getObjectId("_id").toHexString());
		}
		if (document.getString("code") != null){
			status.setCode(document.getString("code"));
		}
		if (document.getString("description") != null){
			status.setDescription(document.getString("description"));
		}
		if (document.getString("name") != null){
			status.setName(document.getString("name"));
		}
		return status;
	}

}
