/**
 * 
 */
package com.xenonteam.xenonlib.main;

import static com.xenonteam.xenonlib.config.Refs.MOD_AUTHORS;
import static com.xenonteam.xenonlib.config.Refs.MOD_DEPS;
import static com.xenonteam.xenonlib.config.Refs.MOD_ID;
import static com.xenonteam.xenonlib.config.Refs.MOD_NAME;
import static com.xenonteam.xenonlib.config.Refs.MOD_VERSION;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.vecmath.Vector3f;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.renderer.block.model.BlockPart;
import net.minecraft.client.renderer.block.model.BlockPartRotation;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ModelBlockDefinition;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.FMLInjectionData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.xenonteam.xenonlib.api.BookRegistry;
import com.xenonteam.xenonlib.api.book.InfoBookContent;
import com.xenonteam.xenonlib.api.interfaces.IXenonMod;
import com.xenonteam.xenonlib.blocks.BlockTest;
import com.xenonteam.xenonlib.client.gui.GuiHandler;
import com.xenonteam.xenonlib.client.gui.GuiTest;
import com.xenonteam.xenonlib.client.gui.element.ElementLoader.LoaderInfo;
import com.xenonteam.xenonlib.client.gui.element.GuiElementImage;
import com.xenonteam.xenonlib.client.gui.element.IGuiElement;
import com.xenonteam.xenonlib.client.model.XenonModelRegistry;
import com.xenonteam.xenonlib.client.render.SpriteSheet;
import com.xenonteam.xenonlib.common.networking.DescriptionHandler;
import com.xenonteam.xenonlib.common.networking.packet.MessageHandleGuiButtonPress;
import com.xenonteam.xenonlib.common.networking.packet.MessageHandleTextUpdate;
import com.xenonteam.xenonlib.common.networking.packet.NetworkHandler;
import com.xenonteam.xenonlib.config.Refs;
import com.xenonteam.xenonlib.proxy.IXenonProxy;
import com.xenonteam.xenonlib.registry.Register;
import com.xenonteam.xenonlib.registry.RegistryHelper;
import com.xenonteam.xenonlib.tileentity.GenericTileEntity;
import com.xenonteam.xenonlib.tileentity.TETest;
import com.xenonteam.xenonlib.util.Log;
import com.xenonteam.xenonlib.util.XUtils;
import com.xenonteam.xenonlib.util.java.ReflectionHelper;
import com.xenonteam.xenonlib.util.java.SortingUtils;

/**
 * @author tim4242
 * @author philipas
 *
 */

@Mod(modid = MOD_ID, name = MOD_NAME, version = MOD_VERSION, dependencies = MOD_DEPS)
public final class XenonLib implements IXenonMod
{

	@SidedProxy(clientSide = "com.xenonteam.xenonlib.proxy.ClientProxy", serverSide = "com.xenonteam.xenonlib.proxy.ServerProxy")
	public static IXenonProxy PROXY;

	@Mod.Instance(MOD_ID)
	public static XenonLib INSTANCE;

	@Mod.Metadata(MOD_ID)
	private static ModMetadata METADATA;

	@Register(unlocName = "test", modid = Refs.MOD_ID)
	public static BlockTest test = new BlockTest();

	private ArrayList<IXenonMod> m_plugins;
	private ArrayList<Class<?>> m_toRegister;
	
	

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Refs.DEBUG = true;

		Log.debug("+---------------------------+", "| Started loading " + MOD_ID + " |", "| Name: " + MOD_NAME + "           |", "| Version: " + MOD_VERSION + "            |", "+---------------------------+");

		m_plugins = new ArrayList<IXenonMod>();
		m_toRegister = new ArrayList<Class<?>>();

		METADATA.modId = MOD_ID;
		METADATA.name = MOD_NAME;
		METADATA.version = MOD_VERSION;
		METADATA.authorList = Arrays.asList(MOD_AUTHORS);

		METADATA.autogenerated = false;
		Refs.CONFIG_PATH = new File(((File) (FMLInjectionData.data()[6])).getAbsolutePath().replace(File.separatorChar, '/').replace("/.", "") + File.separatorChar + "config" + File.separatorChar);

		DescriptionHandler.init();
		NetworkHandler.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new GuiHandler());

		m_plugins.add(INSTANCE);

		Configuration config = new Configuration(new File(event.getModConfigurationDirectory() + "/xenon/xenon.cfg"), "Xenon Config");

		config.load();

		config.get("test", "test", false);

		config.save();
		
		PROXY.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{

		for (IXenonMod mod : m_plugins)
		{
			m_toRegister.addAll(Arrays.asList(mod.getRegisterClasses()));
			
			if(mod.getBookInfo() != null)
			{
				BookRegistry.registerContentForMod(mod.getModInfo().ModID, mod.getBookInfo());
			}
		}

		for (Class<?> c : m_toRegister)
		{
			RegistryHelper.registerObjects(c);
		}

		XenonModelRegistry.initRegistry();
		
		PROXY.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

		Log.debug("+----------------------------+", "| Finished loading " + MOD_ID + " |", "| Name: " + MOD_NAME + "            |", "| Version: " + MOD_VERSION + "             |", "+----------------------------+");

		ResourceLocation key = new ResourceLocation("xenon_lib:models/selfmade/test");

		PROXY.postInit(event);
	}

	public static void addXenonMod(IXenonMod mod)
	{
		XenonLib.INSTANCE.m_plugins.add(mod);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xenonteam.xenonlib.main.IXenonMod#getRegisterClasses()
	 */
	@Override
	public Class<?>[] getRegisterClasses()
	{
		Class<?>[] classes = new Class<?>[] { XenonLib.class, TETest.class, GenericTileEntity.class, MessageHandleTextUpdate.class, MessageHandleGuiButtonPress.class };
		return classes;
	}
	
	@Override
	public InfoBookContent getBookInfo()
	{
		return new InfoBookContent(MOD_ID, "info_book", CreativeTabs.tabTools, "Xenon Lib Info Book", null, null);
	}
	
	@Override
	public ModInfo getModInfo()
	{
		return Refs.MOD_INFO;
	}

}
