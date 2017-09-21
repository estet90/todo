package ru.kononov.todo.api.utils;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

import ru.kononov.todo.api.codecs.LocalDateTimeCodec;
import ru.kononov.todo.api.codecs.TaskCodecProvider;
import ru.kononov.todo.api.codecs.TaskStatusCodecProvider;
import ru.kononov.todo.api.exceptions.TodoException;

@RequestScoped
public class MongodbConnector implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ConfigManager configManager;
	
	private MongoClient mongoClient;

	private static CodecRegistry codecRegistry;
	private static MongoClientOptions options; 

	static {
		codecRegistry = CodecRegistries.fromRegistries(CodecRegistries.fromCodecs(new LocalDateTimeCodec()),
				CodecRegistries.fromProviders(new TaskCodecProvider(), new TaskStatusCodecProvider()),
				MongoClient.getDefaultCodecRegistry());
		options = MongoClientOptions.builder().codecRegistry(codecRegistry).build();
	}
	
	public MongodbConnector() {}
	
	@PostConstruct
	private void connect() throws TodoException {
		String host = configManager.getProperty(ConfigManager.MONGO_DB_HOST_PARAM_NAME);
		String port = configManager.getProperty(ConfigManager.MONGO_DB_PORT_PARAM_NAME);
		connect(host, Integer.parseInt(port));
	}

	void connect(String mongoDbHost, int mongoDbPort){
		ServerAddress address = new ServerAddress(mongoDbHost, mongoDbPort);
		mongoClient = new MongoClient(address, options);
	}
	
	@PreDestroy
	void disconnect() {
		mongoClient.close();
	}

	public MongoClient getMongoClient() {
		return mongoClient;
	}

	public static CodecRegistry getCodecRegistry() {
		return codecRegistry;
	}
}
