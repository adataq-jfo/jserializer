package org.adataq.jserializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.FIELD})
public @interface SerializationAttribute {
	
	/**
	 * The name that will be used in serialization process
	 * @return
	 */
	public String name() default "";
	
	/**
	 * The patterns used to parse or format the field value in serialization process
	 * @return
	 */
	public String[] pattern() default {};

}
