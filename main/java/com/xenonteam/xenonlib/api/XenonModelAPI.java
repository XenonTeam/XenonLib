/**
 * 
 */
package com.xenonteam.xenonlib.api;

import java.lang.reflect.InvocationTargetException;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class XenonModelAPI
{

	public static boolean addModel(ResourceLocation loc, IModel model)
	{
		XenonAPI.initAPI();
		
		try
		{
			return (Boolean) XenonAPI.m_addModel.invoke(loc, model);
		} catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean acceptsModel(ResourceLocation loc)
	{
		XenonAPI.initAPI();
		
		try
		{
			return (Boolean) XenonAPI.m_accepsModel.invoke(loc);
		} catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static IModel loadModel(ResourceLocation loc)
	{
		XenonAPI.initAPI();
		
		try
		{
			return (IModel) XenonAPI.m_loadModel.invoke(loc);
		} catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
}
