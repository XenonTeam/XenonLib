/**
 * 
 */
package com.xenonteam.xenonlib.client.model;

import java.util.HashMap;

import com.google.common.collect.Maps;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class XenonModelRegistry implements ICustomModelLoader
{

	private static XenonModelRegistry m_inst;
	private static boolean m_init = false;
	private static HashMap<ResourceLocation, IModel> m_models;

	public static void initRegistry()
	{
		if(!m_init)
		{
			m_inst = new XenonModelRegistry();
			ModelLoaderRegistry.registerLoader(m_inst);
			m_init = true;
		}
	}

	public static boolean addModel(ResourceLocation loc, IModel model)
	{
		if (!m_models.containsKey(loc) && loc != null && model != null)
		{
			m_models.put(loc, model);
		}

		return false;
	}

	private XenonModelRegistry()
	{
		m_models = Maps.newHashMap();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.client.resources.IResourceManagerReloadListener#
	 * onResourceManagerReload(net.minecraft.client.resources.IResourceManager)
	 */
	@Override
	public void onResourceManagerReload(IResourceManager resourceManager)
	{

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraftforge.client.model.ICustomModelLoader#accepts(net.minecraft
	 * .util.ResourceLocation)
	 */
	@Override
	public boolean accepts(ResourceLocation loc)
	{
		if (m_models.containsKey(loc))
			return true;

		return false;
	}
	
	public boolean acceptsS(ResourceLocation loc)
	{
		initRegistry();
		
		return m_inst.accepts(loc);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraftforge.client.model.ICustomModelLoader#loadModel(net.minecraft
	 * .util.ResourceLocation)
	 */
	@Override
	public IModel loadModel(ResourceLocation loc)
	{
		if (accepts(loc))
			return m_models.get(loc);

		return null;
	}
	
	public IModel loadModelS(ResourceLocation loc)
	{
		initRegistry();
		
		return m_inst.loadModel(loc);
	}

}
