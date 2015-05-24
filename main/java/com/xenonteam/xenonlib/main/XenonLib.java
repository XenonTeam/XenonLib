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
import net.minecraft.client.renderer.block.model.BlockPart;
import net.minecraft.client.renderer.block.model.BlockPartRotation;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ModelBlockDefinition;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
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
import com.xenonteam.xenonlib.api.main.IXenonMod;
import com.xenonteam.xenonlib.blocks.BlockTest;
import com.xenonteam.xenonlib.client.gui.GuiHandler;
import com.xenonteam.xenonlib.client.gui.element.GuiElementImage;
import com.xenonteam.xenonlib.client.gui.element.IGuiElement;
import com.xenonteam.xenonlib.client.render.SpriteSheet;
import com.xenonteam.xenonlib.common.networking.DescriptionHandler;
import com.xenonteam.xenonlib.common.networking.packet.MessageHandleGuiButtonPress;
import com.xenonteam.xenonlib.common.networking.packet.MessageHandleTextUpdate;
import com.xenonteam.xenonlib.common.networking.packet.NetworkHandler;
import com.xenonteam.xenonlib.config.Refs;
import com.xenonteam.xenonlib.proxy.IXenonProxy;
import com.xenonteam.xenonlib.registry.Register;
import com.xenonteam.xenonlib.registry.RegistryHelper;
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

	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{

		for (IXenonMod mod : m_plugins)
		{
			m_toRegister.addAll(Arrays.asList(mod.getRegisterClasses()));
		}

		for (Class<?> c : m_toRegister)
		{
			RegistryHelper.registerObjects(c);
			Log.debug("Registered " + c.toString());
		}

		SpriteSheet.addSpriteSheet("test", new ResourceLocation("xenon_lib:textures/gui/sprites/test.png"));
		
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		final Gson GSON = (new GsonBuilder()).registerTypeAdapter(XenonLib.class, new XenonLib.Deserializer()).create();
		try
		{
			GSON.fromJson(new FileReader(new File("stone.json")), XenonLib.class);
		} catch (JsonSyntaxException e)
		{
			e.printStackTrace();
		} catch (JsonIOException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		Log.debug("+----------------------------+", "| Finished loading " + MOD_ID + " |", "| Name: " + MOD_NAME + "            |", "| Version: " + MOD_VERSION + "             |", "+----------------------------+");

		ResourceLocation key = new ResourceLocation("xenon_lib:models/selfmade/test");

		ArrayList<BlockPart> parts = new ArrayList<BlockPart>();

		HashMap<String, String> texes = new HashMap<String, String>();

		parts.add(new BlockPart(new Vector3f((float) 0.5, (float) 0.5, (float) 0.5), new Vector3f(1, 1, 1), Maps.newHashMap(), new BlockPartRotation(new Vector3f((float) 0.5, (float) 0.5, (float) 0.5), Axis.X, 1, true), true));

		try
		{
			Class[] subs = ModelLoader.class.getDeclaredClasses();

			for (Class clazz : subs)
			{
				Constructor[] constructs = clazz.getConstructors();

				for (Constructor c : constructs)
				{
					c.setAccessible(true);
					Log.info(c);
				}
			}

			XUtils.injectBlockModel(key, parts, texes, true, ItemCameraTransforms.DEFAULT);
			
			Field mmanager_field = ReflectionHelper.getFieldAccesseble(Minecraft.class, "modelManager");
			Log.info(mmanager_field);
			
			ModelManager mmanager = (ModelManager) mmanager_field.get(Minecraft.getMinecraft());
			
			Log.info(mmanager.getModel(new ModelResourceLocation("xenon_lib:models/selfmade/test")));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		

		HashMap<String, IGuiElement> elements = new HashMap<String, IGuiElement>();

		for (int i = 0; i < 11; i++)
		{
			elements.put("test_" + i, new GuiElementImage(null, "test", "test1"));
		}

		elements.get("test_0").setPriority(3);
		elements.get("test_1").setPriority(77);
		elements.get("test_2").setPriority(4);
		elements.get("test_3").setPriority(0);
		elements.get("test_4").setPriority(344);
		elements.get("test_5").setPriority(9);
		elements.get("test_6").setPriority(4);
		elements.get("test_7").setPriority(4);
		elements.get("test_8").setPriority(7);
		elements.get("test_9").setPriority(1895);
		elements.get("test_10").setPriority(12);

		List<String> testList = SortingUtils.sortGuiElements(elements);

		for (String s : testList)
			Log.info(s + ":" + elements.get(s).getPriority());
		
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
		Class<?>[] classes = new Class<?>[] { XenonLib.class, MessageHandleTextUpdate.class, MessageHandleGuiButtonPress.class };
		return classes;
	}

	@SideOnly(Side.CLIENT)
	public static class Deserializer implements JsonDeserializer
	{
		private static final String __OBFID = "CL_00002497";

		public ModelBlockDefinition parseModelBlockDefinition(JsonElement p_178336_1_, Type p_178336_2_, JsonDeserializationContext p_178336_3_)
		{
			JsonObject jsonobject = p_178336_1_.getAsJsonObject();
			List list = this.parseVariantsList(p_178336_3_, jsonobject);
			System.out.println(list);
			return new ModelBlockDefinition((Collection) list);
		}

		protected List parseVariantsList(JsonDeserializationContext p_178334_1_, JsonObject p_178334_2_)
		{
			JsonObject jsonobject1 = JsonUtils.getJsonObject(p_178334_2_, "variants");
			ArrayList arraylist = Lists.newArrayList();
			Iterator iterator = jsonobject1.entrySet().iterator();

			while (iterator.hasNext())
			{

				Entry entry = (Entry) iterator.next();
				// arraylist.add(this.parseVariants(p_178334_1_, entry));
				System.out.println(entry);
			}

			return arraylist;
		}

		protected ModelBlockDefinition.Variants parseVariants(JsonDeserializationContext p_178335_1_, Entry p_178335_2_)
		{
			String s = (String) p_178335_2_.getKey();
			ArrayList arraylist = Lists.newArrayList();
			JsonElement jsonelement = (JsonElement) p_178335_2_.getValue();

			if (jsonelement.isJsonArray())
			{
				Iterator iterator = jsonelement.getAsJsonArray().iterator();

				while (iterator.hasNext())
				{
					JsonElement jsonelement1 = (JsonElement) iterator.next();
					arraylist.add((ModelBlockDefinition.Variant) p_178335_1_.deserialize(jsonelement1, ModelBlockDefinition.Variant.class));
				}
			} else
			{
				arraylist.add((ModelBlockDefinition.Variant) p_178335_1_.deserialize(jsonelement, ModelBlockDefinition.Variant.class));
			}

			return new ModelBlockDefinition.Variants(s, arraylist);
		}

		public Object deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_)
		{
			JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
			List list = this.parseVariantsList(p_deserialize_3_, jsonobject);
			System.out.println(list);

			return null;
		}
	}

}
