package org.adataq.jserializer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.adataq.jserializer.utils.ReflectionUtils;

public class ClassMetadata {
	
	private Map<String, SerializationField> fields = new HashMap<>(8);
	
	private Class<?> type;
	
	public ClassMetadata(Class<?> type) {
		this.type = type;
	}
		
	
	public Map<String, SerializationField> getFields() {
		return fields;
	}
	
	public Class<?> getType() {
		return type;
	}
	
	public static ClassMetadata loadMetadata(Class<?> clazz) {
		
		ClassMetadata metadata = new ClassMetadata(clazz);
		
		//Get all fields from the object class (including from the superclasses...)
		for (Field field : ReflectionUtils.getDeclaredFieldsIncludingSuperclasses(clazz)) {			
			addSerializationField(clazz, field, metadata);
		}
		
		return metadata;
	}
	
	/**
	 * Add an serialization field. If the field has an valid get and set method it will be stored to be used
	 * when user call SerializationField.getValueFromGetMethodOrField(Object)
	 * @param clazz - The class to be stored
	 * @param field
	 * @param metadata
	 * @see SerializationField
	 */
	private static void addSerializationField(Class<?> clazz, Field field, ClassMetadata metadata) {
		Method getMethod = null;
		Method setMethod = null;
		
		
		//Try to find the getMethod of the field
		try {
			getMethod = ReflectionUtils.findGetMethodOf(clazz, field);
		} catch (NoSuchMethodException | SecurityException e) {}
		
		//Try to find the set method of the field
		try {
			setMethod = ReflectionUtils.findSetMethodOf(clazz, field);
		} catch (NoSuchMethodException | SecurityException e) {}
		
		metadata.fields.put(field.getName(), new SerializationField(field, getMethod, setMethod));
		
	}
}
