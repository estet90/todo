package ru.kononov.todo.api.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.bson.BsonDocument;
import org.bson.BsonObjectId;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import ru.kononov.todo.api.entities.BaseEntity;
import ru.kononov.todo.api.entities.exceptions.TodoException;
import ru.kononov.todo.api.utils.ConfigManager;
import ru.kononov.todo.api.utils.MongodbConnector;

/**
 * 
 * @author admin
 *
 * @param <T>
 */
//TODO сделать нормальное описание
public abstract class BaseEntityBean<T extends BaseEntity> {

	@Inject
	protected MongodbConnector connector;
	
	@Inject
	private ConfigManager configManager;
	
	protected abstract Class<T> getClassName();
	
	protected abstract String getCollectionName();
	
	/**
	 * 
	 * @param entity
	 * @throws TodoException 
	 */
	public void create(T entity) throws TodoException {
		entity.setId(new ObjectId().toHexString());
		getCollection().insertOne(entity);
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws TodoException 
	 */
	public T selectOne(String id) throws TodoException {
		BsonDocument document = new BsonDocument("_id", new BsonObjectId(new ObjectId(id)));
		T entity = getCollection().find(document).first();
		return entity;
	}

	/**
	 * 
	 * @return
	 * @throws TodoException 
	 */
	public List<T> selectAll() throws TodoException {
		FindIterable<T> entitiesIterable = getCollection().find();
		List<T> entities = new ArrayList<>(0);
		for (T entity : entitiesIterable){
			entities.add(entity);
		}
		return entities;
	}

	/**
	 * 
	 * @param entity
	 * @param newEntity
	 * @return
	 * @throws TodoException 
	 */
	public T update(T newEntity) throws TodoException {
		T entity = selectOne(newEntity.getId());
		getCollection().updateOne(createDocument(entity), createDocument(newEntity));
		return newEntity;

	}

	/**
	 * 
	 * @param entity
	 * @throws TodoException 
	 */
	public void delete(T entity) throws TodoException {
		BsonDocument document = createDocument(entity);
		getCollection().deleteOne(document);	
	}
	
	/**
	 * 
	 * @param entity
	 * @return
	 * @throws TodoException 
	 */
	public List<T> filter(T filter) throws TodoException {
		BsonDocument document = createDocument(filter);
		FindIterable<T> entitiesIterable = getCollection().find(document);
		List<T> entities = new ArrayList<>(0);
		for (T entity : entitiesIterable){
			entities.add(entity);
		}
		return entities;
	}
	
	protected MongoCollection<T> getCollection() throws TodoException {
		String mongoDbName = configManager.getProperty(ConfigManager.MONGO_DB_NAME_PARAM_NAME);
		return connector.getMongoClient().getDatabase(mongoDbName).getCollection(getCollectionName(), getClassName());
	}
	
	protected BsonDocument createDocument(T entity){
		return entity.toBsonDocument(getClassName(), MongodbConnector.getCodecRegistry());
	}

}
