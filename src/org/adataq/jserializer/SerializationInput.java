package org.adataq.jserializer;

/**
 * Defines specific values for an attribute while parsing
 * @author Felipe Oliveira
 *
 */
public @interface SerializationInput {
	
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
