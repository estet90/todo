package ru.kononov.todo.api.codecs;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

import ru.kononov.todo.api.entities.Task;

public class TaskCodecProvider implements CodecProvider {

	@SuppressWarnings("unchecked")
	@Override
	public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
		if (clazz == Task.class){
			return (Codec<T>)new TaskCodec(registry);
		}
		return null;
	}

}
