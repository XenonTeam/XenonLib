/**
 * 
 */
package com.xenonteam.xenonlib.api;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;

import com.xenonteam.xenonlib.api.interfaces.IXenonMod;
import com.xenonteam.xenonlib.main.XenonLib;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class XenonAPI
{

	static boolean m_isXenonLoaded = false;
	static boolean m_init = false;

	static Class<?> m_xenonMain;
	static Method m_addXenonMod;
	
	static Class<?> m_modelReg;
	static Method m_addModel;
	static Method m_accepsModel;
	static Method m_loadModel;

	public static void initAPI()
	{
		try
		{
			if(m_init == false)
			{
				m_init = true;
				m_xenonMain = ClassLoader.getSystemClassLoader().loadClass("com.xenonteam.xenonlib.main.XenonLib");
				m_isXenonLoaded = true;

				if(m_isXenonLoaded)
				{
					m_addXenonMod = m_xenonMain.getDeclaredMethod("addXenonMod", IXenonMod.class);
					
					m_modelReg = ClassLoader.getSystemClassLoader().loadClass("com.xenonteam.xenonlib.client.model.XenonModelRegistry");
					
					m_addModel = m_modelReg.getDeclaredMethod("addModel", ResourceLocation.class, IModel.class);
					m_accepsModel = m_modelReg.getDeclaredMethod("accepts", ResourceLocation.class);
					m_loadModel = m_modelReg.getDeclaredMethod("loadModel", ResourceLocation.class);
				}
			}
		} catch(Exception e)
		{
			m_isXenonLoaded = false;
		}
	}

	public static boolean isInit()
	{
		return m_init;
	}

	public static boolean isXenonLoaded()
	{
		return m_isXenonLoaded;
	}

	public static void addXenonMod(IXenonMod mod) throws Exception
	{
		initAPI();
		
		try
		{
			if(isXenonLoaded())
			{

				if(!Loader.instance().hasReachedState(LoaderState.POSTINITIALIZATION))
				{
					m_addXenonMod.invoke(null, mod);
				}

			}

		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
