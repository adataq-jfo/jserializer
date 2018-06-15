package org.adataq.jserializer;

/**
 * Define specific value from output while serializing an object
 * @author Felipe Oliveira
 *
 */
public @interface SerializationOutput{
	
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
