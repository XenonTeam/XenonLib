/**
 * 
 */
package com.xenonteam.xenonlib.config.struct;

import net.minecraftforge.common.config.Configuration;

/**
 * @author tim4242
 * @author philipas
 *
 */
public interface IConfigType<T>
{	
	
	/**
	 * 
	 * Returns an instance of this, that is parsed from the string that is marked by the given key.
	 * 
	 * @param category The category to be placed in
	 * @param key The key to be called
	 * @param defaultValue The default value
	 * @param comment A comment that will be put above
	 * @return An object, or null if the given {@link java.lang.String String} is invalid
	 */
	public T get(Configuration config, String category, String key, String defaultValue, String comment);
	
	/**
	 * 
	 * Returns an array of instances of this, that are parsed from the string that is marked by the given key.
	 * 
	 * @param category The category to be placed in
	 * @param key The key to be called
	 * @param defaultValue The default value
	 * @param comment A comment that will be put above
	 * @return An object array, or null if the given {@link java.lang.String String} is invalid
	 */
	public T get(Configuration config, String category, String key, String[] defaultValues, String comment);
	
	/**
	 * 
	 * Parsed an instance of this from the given {@link java.lang.String String}.
	 * 
	 * @param value A {@link java.lang.String String} to be parsed
	 * @return An object, or null if the given {@link java.lang.String String} is invalid
	 */
	public T parse(String value);
}
