package ru.kononov.todo.api.utils;

import static org.junit.Assert.*;

import org.bson.BsonDocument;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import ru.kononov.todo.api.entities.TaskStatus;
import ru.kononov.todo.api.entities.TaskStatus.TaskStatusCode;
import ru.kononov.todo.api.exceptions.TodoException;

public class MongodbConnectorTest {

	private MongodbConnector connector;
	private MongodExecutable executable;
	ConfigManager configManager; 
	
	@Before
	public void setUp() throws Exception {
		configManager = new ConfigManager("config_test.properties");
		String host = configManager.getProperty(ConfigManager.MONGO_DB_HOST_PARAM_NAME);
		int port = Integer.parseInt(configManager.getProperty(ConfigManager.MONGO_DB_PORT_PARAM_NAME));
		IMongodConfig mongodConfig = new MongodConfigBuilder()
			.version(Version.Main.PRODUCTION)
			.net(new Net(host, port, Network.localhostIsIPv6()))
			.build();
		MongodStarter starter = MongodStarter.getDefaultInstance();
		executable = starter.prepare(mongodConfig);
		executable.start();
		connector = new MongodbConnector();
		connector.connect(host, port);
	}

	@After
	public void tearDown() throws Exception {
		connector.disconnect();
		executable.stop();
	}

	@Test
	public void testMongoClient() throws TodoException {
		MongoClient client = connector.getMongoClient();
		String databseName = configManager.getProperty(ConfigManager.MONGO_DB_NAME_PARAM_NAME); 
		MongoDatabase database = client.getDatabase(databseName);
		MongoCollection<TaskStatus> statusCollection = database.getCollection(TaskStatus.class.getSimpleName(), TaskStatus.class);
		TaskStatus status = new TaskStatus(TaskStatusCode.COMPLETE.name());
		BsonDocument document = status.toBsonDocument(TaskStatus.class, MongodbConnector.getCodecRegistry());
		statusCollection.insertOne(status);
		status = statusCollection.find(document).first();
		assertEquals(status.getCode(), TaskStatusCode.COMPLETE.name());
	}

}
