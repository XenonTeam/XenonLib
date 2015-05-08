/**
 * 
 */
package com.xenonteam.xenonlib.util.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.xenonteam.xenonlib.util.Log;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public class ReflectionHelper
{
	/**
	 * Get a accessible {@link java.lang.reflect.Method Method} from a
	 * {@link java.lang.Class Class} that you normally can't use.
	 * 
	 * @param temp
	 *            The {@link java.lang.Class Class} that you want to get the
	 *            {@link java.lang.reflect.Method Method} form.
	 * @param methodName
	 *            The {@link java.lang.String String} that is the name of
	 *            {@link java.lang.reflect.Method Method} you want to get.
	 * @param parameters
	 *            The parameters of the {@link java.lang.reflect.Method Method}
	 *            that you want to get.
	 * @return The {@link java.lang.reflect.Method Method} that you requested
	 *         and makes them accessible.
	 */
	public static Method getMethodAccesseble(Class<?> temp, String methodName, Class<?>... parameters)
	{
		Method method = getSpecificMethod(temp, methodName, parameters);
		method.setAccessible(true);
		return method;
	}

	/**
	 * Get an accessible {@link java.lang.reflect.Field Field} from a
	 * {@link java.lang.Class Class} that you normally can't use.
	 * 
	 * @param temp
	 *            The {@link java.lang.Class Class} that you want to get the
	 *            {@link java.lang.reflect.Field Field} form.
	 * @param fieldName
	 *            The {@link java.lang.String String} that is the name of
	 *            {@link java.lang.reflect.Field Field} you want to get.
	 * @return The {@link java.lang.reflect.Field Field} that you requested and
	 *         makes them accessible.
	 */
	public static Field getFieldAccesseble(Class<?> temp, String fieldName)
	{
		Field field = getField(temp, fieldName);
		field.setAccessible(true);
		return field;
	}

	/**
	 * Get an accessible {@link java.lang.reflect.Constructor Constructor} from
	 * a {@link java.lang.Class Class} that you normally can't use.
	 * 
	 * @param temp
	 *            The {@link java.lang.Class Class} that you want to get the
	 *            {@link java.lang.reflect.Constructor Constructor} form.
	 * @param parameters
	 *            The parameters of the {@link java.lang.reflect.Constructor
	 *            Constructor} that you want to get.
	 * @return The {@link java.lang.reflect.Constructor Constructor} that you
	 *         requested and makes them accessible.
	 */
	public static Constructor getConstructorAccesseble(Class<?> temp, Class... parameters)
	{
		Constructor constructor = getConstructor(temp, parameters);
		constructor.setAccessible(true);
		return constructor;
	}

	/**
	 * Get a {@link java.lang.reflect.Constructor Constructor} from a
	 * {@link java.lang.Class Class} that you normally can't use.
	 * 
	 * @param temp
	 *            The {@link java.lang.Class Class} that you want to get the
	 *            {@link java.lang.reflect.Constructor Constructor} form.
	 * @param parameters
	 *            The parameters of the {@link java.lang.reflect.Constructor
	 *            Constructor} that you want to get.
	 * @return The {@link java.lang.reflect.Constructor Constructor} that you
	 *         requested and makes them accessible.
	 */
	public static Constructor getConstructor(Class<?> temp, Class... parameters)
	{
		try
		{
			Constructor constructor = temp.getDeclaredConstructor(parameters);
			return constructor;
		} catch (NoSuchMethodException e)
		{
			Log.error("There is no constructor for the class : " + temp.toString() + " :");
			return null;
		} catch (SecurityException e)
		{
			Log.error("you can not access the constructor of this class : " + temp.toString() + " :");
			return null;
		}
	}

	/**
	 * Get a {@link java.lang.reflect.Method Method} from a
	 * {@link java.lang.Class Class} that you normally can't use.
	 * 
	 * @param temp
	 *            The {@link java.lang.Class Class} that you want to get the
	 *            {@link java.lang.reflect.Method Method} form.
	 * @param parameters
	 *            The parameters of the {@link java.lang.reflect.Method Method}
	 *            that you want to get.
	 * @return The {@link java.lang.reflect.Method Method} that you requested
	 *         and makes them accessible.
	 */
	public static Method getSpecificMethod(Class<?> temp, String methodName, Class<?>... parameters)
	{
		try
		{
			return temp.getDeclaredMethod(methodName, parameters);
		} catch (NoSuchMethodException e)
		{
			Log.error("there is no method by this name : " + methodName + " :");
			return null;
		} catch (SecurityException e)
		{
			Log.error("you can not access the method by this name : " + methodName + " :");
			return null;
		}
	}

	/**
	 * This is for internal use only!
	 */
	@Deprecated
	public static Method getMethod(Class<?> temp, String methodName)
	{
		Method[] methods = temp.getMethods();

		for (int i = 0; i < methods.length; i++)
		{
			if (methods[i].getName().equals(methodName))
			{
				return methods[i];
			}
		}
		return null;
	}

	/**
	 * Get a {@link java.lang.reflect.Field Field} from a
	 * {@link java.lang.Class Class} that you normally can't use.
	 * 
	 * @param temp
	 *            The {@link java.lang.Class Class} that you want to get the
	 *            {@link java.lang.reflect.Field Field} form.
	 * @param parameters
	 *            The parameters of the {@link java.lang.reflect.Field Field}
	 *            that you want to get.
	 * @return The {@link java.lang.reflect.Field Field} that you requested and
	 *         makes them accessible.
	 */
	public static Field getField(Class<?> temp, String fieldName)
	{
		try
		{
			return temp.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e)
		{
			Log.error("there is no field by this name : " + fieldName + " :");
			return null;
		} catch (SecurityException e)
		{
			Log.error("you can not access the field by this name : " + fieldName + " :");
			return null;
		}

	}

}
