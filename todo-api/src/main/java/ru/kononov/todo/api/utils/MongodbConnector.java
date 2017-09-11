package ru.kononov.todo.api.utils;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

import ru.kononov.todo.api.codecs.LocalDateTimeCodec;
import ru.kononov.todo.api.codecs.TaskCodecProvider;
import ru.kononov.todo.api.codecs.TaskStatusCodecProvider;
import ru.kononov.todo.api.entities.exceptions.TodoException;

@RequestScoped
public class MongodbConnector implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LogManager.getLogger(MongodbConnector.class);

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
	
	@PostConstruct
	private void connect() throws TodoException {
		String mongoDbHost = configManager.getProperty(ConfigManager.MONGO_DB_HOST_PARAM_NAME);
		String mongoDbPort = configManager.getProperty(ConfigManager.MONGO_DB_PORT_PARAM_NAME);
		ServerAddress address = new ServerAddress(mongoDbHost, Integer.parseInt(mongoDbPort));
		mongoClient = new MongoClient(address, options);
		LOGGER.debug("connect");
	}

	@PreDestroy
	private void disconnect() {
		mongoClient.close();
		LOGGER.debug("disconnect");
	}

	public MongoClient getMongoClient() {
		return mongoClient;
	}

	public static CodecRegistry getCodecRegistry() {
		return codecRegistry;
	}
}
