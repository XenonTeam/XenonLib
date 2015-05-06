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

	public static Method getMethodAccesseble(Class<?> temp, String methodName, Class<?>... parameters)
	{
		Method method = getSpecificMethod(temp, methodName, parameters);
		method.setAccessible(true);
		return method;
	}

	public static Field getFieldAccesseble(Class<?> temp, String fieldName)
	{
		Field field = getField(temp, fieldName);
		field.setAccessible(true);
		return field;
	}

	public static Constructor getConstructorAccesseble(Class<?> temp, Class... parameters)
	{
		Constructor constructor = getConstructor(temp, parameters);
		constructor.setAccessible(true);
		return constructor;
	}

	public static Constructor getConstructor(Class<?> temp, Class... parameters)
	{
		try
		{
			Constructor constructor = temp.getConstructor(parameters);
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

	public static Method getSpecificMethod(Class<?> temp, String methodName, Class<?>... parameters)
	{
		try
		{
			return temp.getMethod(methodName, parameters);
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

	public static Field getField(Class<?> temp, String fieldName)
	{
		try
		{
			return temp.getField(fieldName);
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

	@Deprecated
	public static boolean hasMethod(Class<?> temp, String methodName)
	{
		Method[] methods = temp.getMethods();

		for (int i = 0; i < methods.length; i++)
		{
			if (methods[i].getName().equals(methodName))
			{
				return true;
			}
		}
		return false;
	}

	public static boolean hasSpecificMethod(Class<?> temp, String methodName, Class<?>... parameters)
	{
		try
		{
			temp.getMethod(methodName, parameters);
			return true;
		} catch (NoSuchMethodException e)
		{
			Log.error("there is no method by this name : " + methodName + " :");
			return false;
		} catch (SecurityException e)
		{
			Log.error("you can not access the method by this name : " + methodName + " :");
			return false;
		}
	}

	public static boolean hasField(Class<?> temp, String fieldName)
	{
		try
		{
			temp.getField(fieldName);
			return true;
		} catch (NoSuchFieldException e)
		{
			return false;
		} catch (SecurityException e)
		{
			Log.error("you can not access the field by this name : " + fieldName + " :");
			return false;
		}

	}

}
