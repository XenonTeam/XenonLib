/**
 * 
 */
package com.xenonteam.xenonlib.client.model;

import java.util.Collection;

import com.google.common.base.Function;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.IModelState;

/**
 * @author tim4242
 *
 */
public class XenonModelWrapper implements IModel
{

	public Collection<ResourceLocation> m_deps;
	public Collection<ResourceLocation> m_texs;
	
	public XenonModelWrapper(Collection<ResourceLocation> deps, Collection<ResourceLocation> texs)
	{
		m_deps = deps;
		m_texs = texs;
	}
	
	/* 
	 * @see net.minecraftforge.client.model.IModel#getDependencies()
	 */
	@Override
	public Collection<ResourceLocation> getDependencies()
	{
		return m_deps;
	}

	/* 
	 * @see net.minecraftforge.client.model.IModel#getTextures()
	 */
	@Override
	public Collection<ResourceLocation> getTextures()
	{
		return m_texs;
	}

	/* 
	 * @see net.minecraftforge.client.model.IModel#bake(net.minecraftforge.client.model.IModelState, net.minecraft.client.renderer.vertex.VertexFormat, com.google.common.base.Function)
	 */
	@Override
	public IFlexibleBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter)
	{
		return null;
	}

	/* 
	 * @see net.minecraftforge.client.model.IModel#getDefaultState()
	 */
	@Override
	public IModelState getDefaultState()
	{
		 return ModelRotation.X0_Y0;
	}

}
