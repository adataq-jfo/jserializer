package org.adataq.jserializer.json;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.adataq.jserializer.exceptions.InvalidValueCastException;
import org.adataq.jserializer.exceptions.JsonParseException;

public class JsonValue implements JsonData {
	
	/**
	 * The value
	 */
	private Object originalValue;
	
	/**
	 * Create an instance of the json value
	 */
	public JsonValue() {}
	
	/**
	 * Create an instance of the json value
	 * @param value - The value
	 */
	public JsonValue(Object value) {
		setOriginalValue(value);
	}
	
	public static boolean isJsonRawData(Object value) {
		if(value == null) {
			return true;
		}
		else if(value.getClass().isPrimitive()) {
			return true;
		}else if((value instanceof Boolean) || (value instanceof Number) || (value instanceof String) || (value instanceof Date) || (value instanceof Collection) || (value instanceof Object[]) || (value.getClass().isEnum())) {
			return true;
		}else {
			return false;
		}
	}
	
	public static JsonValue parse(String value) throws JsonParseException {
		//Check if is a string value
		if(value.startsWith("\"") && value.endsWith("\"")) {
			
			if(value.length() < 2) {
				throw new JsonParseException("Invalid JSON String value: " + value);
			}
			
			return new JsonValue(value.substring(1, value.length() - 1));
		
		//Check if is a null value
		}else if(value.toLowerCase().equals("null")) {
			return new JsonValue(null);
		
		//Check if is a Boolean value
		}else if(value.equals("true") || value.equals("false")) {
			return new JsonValue(Boolean.parseBoolean(value));
		//Check if is a integer number
		}else if(value.matches("^\\d+$")) {
			return new JsonValue(Long.parseLong(value));
		}
		//Check if is a float number
		else if(value.matches("^\\d+\\.$")) {
			return new JsonValue(Double.parseDouble(value));
		}
		//Throw parse exception
		else {
			throw new JsonParseException("The value '" + value + "' is not a valid json value");
		}
	}
	
	/**
	 * Cast the JSON value to String
	 * @throws InvalidValueCastException If the value can not be cast to String value
	 * @return
	 */
	public String asString() {
		try {
			return this.originalValue.toString().replaceAll("\\\\\"", "\"");
		} catch (Exception e) {
			throw new InvalidValueCastException("The value '" + this.toString() + "' can not be cast to String");
		}
	}
	
	/**
	 * Cast the JSON value to long
	 * @throws InvalidValueCastException If the value can not be cast to long value
	 * @return
	 */
	public long asLong() {
		try {
			return Long.parseLong(this.asString());
		} catch (Exception e) {
			throw new InvalidValueCastException("The value '" + this.toString() + "' can not be cast to long");
		}
	}
	
	/**
	 * Cast the JSON value to int
	 * @throws InvalidValueCastException If the value can not be cast to int value
	 * @return
	 */
	public int asInt() {
		try {
			return Integer.parseInt(this.asString());
		} catch (Exception e) {
			throw new InvalidValueCastException("The value '" + this.toString() + "' can not be cast to int");
		}
	}
	
	/**
	 * Cast the JSON value to float
	 * @throws InvalidValueCastException If the value can not be cast to float value
	 * @return
	 */
	public float asFloat() {
		try {
			return Float.parseFloat(this.asString());
		} catch (Exception e) {
			throw new InvalidValueCastException("The value '" + this.toString() + "' can not be cast to float");
		}
	}
	
	/**
	 * Cast the JSON value to double
	 * @throws InvalidValueCastException If the value can not be cast to double value
	 * @return
	 */
	public double asDouble() {
		try {
			return Double.parseDouble(this.asString());
		} catch (Exception e) {
			throw new InvalidValueCastException("The value '" + this.toString() + "' can not be cast to double");
		}
	}
	
	/**
	 * Cast the JSON value to {@link JsonObject}.
	 * For safe use of this method, the user must check with JsonValue.isJsonObject() if the value
	 * is, in fact, an instance of {@link JsonObject}
	 * @return
	 */
	public JsonObject asJsonObject() {
		return (JsonObject) this.originalValue;
	}
	
	/**
	 * Cast the JSON value to {@link JsonArray}.
	 * For safe use of this method, the user must check with JsonValue.isJsonArray() if the value
	 * is, in fact, an instance of {@link JsonObject}
	 * @return
	 */
	public JsonArray asJsonArray() {
		return (JsonArray) this.originalValue;
	}
	
	/**
	 * Cast the JSON value to date. This method will consider that the current value of this
	 * JSON value is a string in an date format.
	 * @param format - The form
	 * @throws InvalidValueCastException If the value can not be cast to date value
	 * @return
	 */
	public Date asDate(String format) {
		try {
			return new SimpleDateFormat(format).parse(this.asString());
		} catch (Exception e) {
			throw new InvalidValueCastException("The value '" + this.toString() + "' can not be cast to date");
		}
	}
	
	
	public Date asDate() {
		try {
			return (Date) this.originalValue;
		} catch (Exception e) {
			throw new InvalidValueCastException("The value '" + this.toString() + "' can not be cast to date");
		}
	}
	
	public boolean isNull() {
		return (this.originalValue == null);
	}
	
	public boolean isBoolean() {
		return (this.originalValue != null && (this.originalValue instanceof Boolean));
	}
	
	public boolean isJsonData() {
		return (this.originalValue != null && (this.originalValue instanceof JsonStructure));
	}
	
	public boolean isJsonObject() {
		return (this.originalValue != null && (this.originalValue instanceof JsonObject));
	}
	
	public boolean isJsonArray() {
		return (this.originalValue != null && (this.originalValue instanceof JsonArray));
	}
	
	public boolean isNumeric() {
		return (this.originalValue != null && (this.originalValue instanceof Number));
	}
	
	public boolean isString() {
		return (this.originalValue != null && (this.originalValue instanceof String));
	}
	
	public boolean isDate() {
		return (this.originalValue != null && (this.originalValue instanceof Date));
	}
	
	public boolean isEnum() {
		return (this.originalValue != null && (this.originalValue.getClass().isEnum()));
	}
	
	public boolean isPrimitive() {
		return (this.originalValue != null && this.originalValue.getClass().isPrimitive() || isBoolean() || isString() || isNumeric());
	}

	public Object getOriginalValue() {
		return originalValue;
	}

	@SuppressWarnings("unchecked")
	void setOriginalValue(Object value) {
		this.originalValue = value;
		if(value == null) {
			this.originalValue = null;
		}
		else if (value instanceof JsonValue) {
			this.originalValue = ((JsonValue) value).getOriginalValue();
		}
		else if(value instanceof JsonStructure) {
			this.originalValue = value;
		}
		else if(value != null && !isPrimitive() && !(value instanceof JsonObject)) {
			
			//Check if the value is an Array[] or a Collection
			if((value instanceof Object[])) {
				this.originalValue = new JsonArray((Object[]) value);
			}
			else if ((value instanceof Collection)){
				this.originalValue = new JsonArray(((Collection<Object>) value).toArray());
			}
			
		}
	}

	@Override
	public String toString() {
		if(!isNull()) {
			if(isString() || isEnum()) {
				return "\"" + originalValue.toString() + "\"";
			}
			if(isDate()) {
				return String.valueOf(asDate().getTime());
			}
			return this.originalValue.toString();
		}else {
			return "null";
		}
		
	}
	
	

}
