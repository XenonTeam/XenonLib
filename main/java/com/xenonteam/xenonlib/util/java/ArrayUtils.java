/**
 * 
 */
package com.xenonteam.xenonlib.util.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tim4242
 * 
 * 
 *
 */
public class ArrayUtils
{
	
	public static <T> List<T> asList(T[] arr)
	{
		return (List<T>) new ArrayList<T>(Arrays.asList(arr));
	}
	
	public static <T> ArrayList<T> asArrayList(T[] arr)
	{
		return new ArrayList<T>(Arrays.asList(arr));
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] asArray(List<T> list)
	{
		return (T[]) list.toArray();
	}
	
	public static <T, V> Map<T, V> asMap(List<T> keys, List<V> values)
	{
		if(keys.size() != values.size())
			throw new IllegalArgumentException("keys and values don't have the same length!");
			
		
		HashMap<T, V> res = new HashMap<T, V>(keys.size());
		
			for(int i = 0; i < keys.size(); i++)
				res.put(keys.get(i), values.get(i));
		
		return (Map<T, V>) res;
	}
	
	public static <T, V> Map<T, V> asMap(T[] keys, V[] values)
	{
		return asMap(asList(keys), asList(values));
	}
	
	public static <T> ArrayList<T> getArrayListWithCapacity(int size)
	{
		ArrayList<T> res = new ArrayList<T>(size);
		
		for(int i = 0; i <= size; i++)
			res.add(null);
		
		return res;
	}
	
	
	
}
