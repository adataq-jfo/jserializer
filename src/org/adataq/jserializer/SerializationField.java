package org.adataq.jserializer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class SerializationField {
	
	private String outputName;
	
	private String[] outputPattern;
	
	private String inputName;
	
	private String[] inputPattern;
	
	private Field field;
	
	private Method getMethod;
	
	private Method setMethod;
	
	private boolean isReadable = true;
	
	private boolean isWriteable = true;
		
	public SerializationField(Field field, Method getMethod, Method setMethod) {
		this.field = field;
		this.getMethod = getMethod;
		this.setMethod = setMethod;
		this.outputName = field.getName();
		this.inputName = field.getName();
		processFieldAnnotations();
	}
	
	/**
	 * Read {@link SerializationAccess} annotation from the field and check if is readable and writable.
	 * The defualt value from this fields is <b>true</b>.<br/>
	 */
	private void processFieldAnnotations() {
		
		//Get each annotation from field
		for (Annotation ann : field.getAnnotations()) {
			
			//Check for access data (writable and readable)
			if(ann instanceof SerializationAccess) {
				isReadable = ((SerializationAccess) ann).readable();
				isWriteable = ((SerializationAccess) ann).writable();
			}
			//Check for attribute specific parameters (name and pattern)
			else if(ann instanceof SerializationAttribute) {
				outputName = ((SerializationAttribute) ann).name();
				outputPattern = ((SerializationAttribute) ann).pattern();
				
				inputName = ((SerializationAttribute) ann).name();
				inputPattern = ((SerializationAttribute) ann).pattern();
			}
			//Check for specific attributes in output data
			else if(ann instanceof SerializationOutput) {
				outputName = ((SerializationAttribute) ann).name();
				outputPattern = ((SerializationAttribute) ann).pattern();
			}
			//Check for specific attributes in input data
			else if(ann instanceof SerializationInput) {
				inputName = ((SerializationAttribute) ann).name();
				inputPattern = ((SerializationAttribute) ann).pattern();
			}
		}
		
	}
	
	public void setValueFromSetMethodOrField(Object source, Object value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if(setMethod != null) {
			setMethod.invoke(source, value);
		}else {
			field.set(source, value);
		}
	}
	
	public Object getValueFromGetMethodOrField(Object source) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if(getMethod != null) {
			return getMethod.invoke(source);
		}else {
			return this.field.get(source);
		}
	}

	public Method getGetMethod() {
		return getMethod;
	}
	
	public Field getField() {
		return field;
	}
	
	public boolean isReadable() {
		return isReadable;
	}
	
	public boolean isWriteable() {
		return isWriteable;
	}
	
	public boolean isAccessible() {
		return (Modifier.isPublic(field.getModifiers()) || getMethod != null && Modifier.isPublic(getMethod.getModifiers()));
	}
	
	public boolean isRawData() {
		return (field.getType().isPrimitive() || (field.getType().equals(String.class)));
	}

	public String getOutputName() {
		return outputName;
	}

	public String[] getOutputPattern() {
		return outputPattern;
	}

	public String getInputName() {
		return inputName;
	}

	public String[] getInputPattern() {
		return inputPattern;
	}

	public Method getSetMethod() {
		return setMethod;
	}
}
