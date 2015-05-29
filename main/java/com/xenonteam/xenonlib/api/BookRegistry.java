/**
 * 
 */
package com.xenonteam.xenonlib.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.javafx.UnmodifiableArrayList;
import com.xenonteam.xenonlib.api.book.InfoBookContent;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class BookRegistry
{

	private static Map<String, InfoBookContent> m_categs = new HashMap<String, InfoBookContent>();
	
	public static boolean registerContentForMod(String modid, InfoBookContent categ)
	{
		if(m_categs.containsKey(modid))
			return false;
		
		m_categs.put(modid,  categ);
		return true;
	}
	
	public static InfoBookContent getContentForMod(String modid)
	{
		return m_categs.get(modid);
	}
	
}
