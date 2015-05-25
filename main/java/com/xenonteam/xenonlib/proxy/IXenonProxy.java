/**
 * 
 */
package com.xenonteam.xenonlib.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * @author tim4242
 * @author philipas
 * 
 * The proxy interface used by the XenonLib, you can use this one but it's not recommended
 */
public interface IXenonProxy
{

	public void preInit(FMLPreInitializationEvent event);
	
	public void init(FMLInitializationEvent event);
	
	public void postInit(FMLPostInitializationEvent event);
	
}
