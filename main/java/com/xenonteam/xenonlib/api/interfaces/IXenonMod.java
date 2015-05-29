/**
 * 
 */
package com.xenonteam.xenonlib.api.interfaces;

import com.xenonteam.xenonlib.api.book.InfoBookContent;

/**
 * @author tim4242
 * @author philipas
 *
 * <br>
 * <br>
 * 
 * This is an interface to be expanded by all mods that use the XenonLib.
 */
public interface IXenonMod
{

	public static class ModInfo
	{
		public String ModID;
		public String Version;
		
		public ModInfo(String id, String v)
		{
			ModID = id;
			Version = v;
		}
	}
	
	public ModInfo getModInfo();
	
	/**
	 * @return All the classes to be registered
	 */
	public Class<?>[] getRegisterClasses();
	
	/**
	 * 
	 * @return The {@link com.xenonteam.xenonlib.client.gui.element.ElementLoader.LoaderInfo LoaderInfo} for this {@link com.xenonteam.xenonlib.api.interfaces.IXenonMod IXenonMod} book, can be null
	 */
	public InfoBookContent getBookInfo();
	
}
