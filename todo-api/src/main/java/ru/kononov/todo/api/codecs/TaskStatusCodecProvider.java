package ru.kononov.todo.api.codecs;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

import ru.kononov.todo.api.entities.TaskStatus;

/**
 * класс для регистрации кодека TaskStatusCodec
 * 
 * @author admin
 *
 */
public class TaskStatusCodecProvider implements CodecProvider{

	@SuppressWarnings("unchecked")
	@Override
	public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
		if (clazz == TaskStatus.class){
			return (Codec<T>)new TaskStatusCodec(registry);
		}
		return null;
	}

}
