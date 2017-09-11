package ru.kononov.todo.api.entities.convertors;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;

import ru.kononov.todo.api.entities.BaseEntity;

@Deprecated
public abstract class BaseToEntityDocumentConvertor<T extends BaseEntity> {
	
	private static final Logger LOGGER = LogManager.getLogger(BaseToEntityDocumentConvertor.class);
	
	protected List<Field> fields = new ArrayList<>(0);

	public abstract Class<T> getClassName();
	
	public BaseToEntityDocumentConvertor(){
		LOGGER.debug("fill field's names");
		fields.addAll(Arrays.asList(getClassName().getDeclaredFields()));
		fields.addAll(Arrays.asList(getClassName().getSuperclass().getDeclaredFields()));
	}

	public Document convertEntityToDocument(T entity) throws IllegalArgumentException, IllegalAccessException{
		Document document = new Document(); 
		for (Field field : fields){
			field.setAccessible(true);
			if (field.get(entity) != null){
				if (field.getName() == "id"){
					document.put("_id", new ObjectId(entity.getId()));	
				} else {
					document.put(field.getName(), field.get(entity));
				}
			}
		}
		return document;
	}
	
	public T convertDocumentToEntity(Document object) throws InstantiationException, IllegalAccessException{
		T entity = getClassName().newInstance();
		for (Field field : fields){
			field.setAccessible(true);
			String fieldName = field.getName() == "id" ? "_id" : field.getName();
			if (object.get(fieldName) != null){
				if (fieldName == "_id"){
					ObjectId id = (ObjectId) object.get("_id");
					entity.setId(id.toString());
				} else {
					field.set(entity, object.get(fieldName));
				}
			}
		}
		return entity;
	}
	
}
