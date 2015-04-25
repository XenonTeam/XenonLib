/**
 * 
 */
package com.xenonteam.xenonlib.main;


import static com.xenonteam.xenonlib.config.Refs.MOD_AUTHORS;
import static com.xenonteam.xenonlib.config.Refs.MOD_DEPS;
import static com.xenonteam.xenonlib.config.Refs.MOD_ID;
import static com.xenonteam.xenonlib.config.Refs.MOD_NAME;
import static com.xenonteam.xenonlib.config.Refs.MOD_VERSION;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.xenonteam.xenonlib.common.networking.DescriptionHandler;
import com.xenonteam.xenonlib.common.networking.packet.NetworkHandler;
import com.xenonteam.xenonlib.config.Refs;
import com.xenonteam.xenonlib.proxy.IXenonProxy;
import com.xenonteam.xenonlib.registry.RegistryHelper;
import com.xenonteam.xenonlib.util.Log;

/**
 * @author tim4242
 * @author philipas
 *
 */

@Mod(modid = MOD_ID, name = MOD_NAME, version = MOD_VERSION, dependencies = MOD_DEPS)
public class XenonLib 
{
	
	@SidedProxy(clientSide = "com.xenonteam.xenonlib.proxy.ClientProxy", serverSide = "com.xenonteam.xenonlib.proxy.ServerProxy")
	public static IXenonProxy PROXY;
	
	@Mod.Instance(MOD_ID)
	public static XenonLib INSTANCE;
	
	@Mod.Metadata(MOD_ID)
	public static ModMetadata METADATA;
	
	private ArrayList<IXenonMod> m_plugins;
	private ArrayList<Class<?>> m_toRegister;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Refs.DEBUG = true;
		
		m_plugins = new ArrayList<IXenonMod>();
		m_toRegister = new ArrayList<Class<?>>();
		
		METADATA.modId = MOD_ID;
		METADATA.name = MOD_NAME;
		METADATA.version = MOD_VERSION;
		METADATA.authorList = Arrays.asList(MOD_AUTHORS);
		
		METADATA.autogenerated = false;
		DescriptionHandler.init();
		NetworkHandler.init();
		
		Log.debug("+---------------------------+",
				  "| Started loading " + MOD_ID + " |",
				  "| Name: " + MOD_NAME + "           |",
				  "| Version: " + MOD_VERSION + "            |",
				  "+---------------------------+");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		
		for(IXenonMod mod : m_plugins)
		{
			m_toRegister.addAll(Arrays.asList(mod.getRegisterClasses()));
		}
		
		for(Class<?> c : m_toRegister)
		{
			RegistryHelper.registerObjects(c);
			Log.debug("Registered " + c.toString());
		}
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
		
		Log.debug("+----------------------------+",
				  "| Finished loading " + MOD_ID + " |",
				  "| Name: " + MOD_NAME + "            |",
				  "| Version: " + MOD_VERSION + "             |",
				  "+----------------------------+");
	}
	
}
