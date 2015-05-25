/**
 * 
 */
package com.xenonteam.xenonlib.api.interfaces;

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

	/**
	 * @return All the classes to be registered
	 */
	public Class<?>[] getRegisterClasses();
	
}
