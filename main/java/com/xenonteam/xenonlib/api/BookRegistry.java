/**
 * 
 */
package com.xenonteam.xenonlib.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.javafx.UnmodifiableArrayList;
import com.xenonteam.xenonlib.api.book.BookCategory;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class BookRegistry
{

	private static Map<String, BookCategory> m_categs = new HashMap<String, BookCategory>();
	
	public static boolean registerCategory(String modid, BookCategory categ)
	{
		if(m_categs.containsKey(modid))
			return false;
		
		m_categs.put(modid,  categ);
		return true;
	}
	
	public static Map<String, BookCategory> getCategorys()
	{
		return m_categs;
	}
	
}
