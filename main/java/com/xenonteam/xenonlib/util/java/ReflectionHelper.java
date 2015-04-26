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
			Log.error("there is no field by this name");
			return null;
		} catch (SecurityException e)
		{
			Log.error("you can not access this field");
			return null;
		}

	}

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
