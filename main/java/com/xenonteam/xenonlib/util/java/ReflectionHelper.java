/**
 * 
 */
package com.xenonteam.xenonlib.util.java;

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
