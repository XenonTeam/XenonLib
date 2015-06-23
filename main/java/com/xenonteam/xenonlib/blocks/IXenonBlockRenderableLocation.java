/**
 * 
 */
package com.xenonteam.xenonlib.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

/**
 * @author tim4242
 *
 */
public interface IXenonBlockRenderableLocation
{

	public ResourceLocation getTextureLocation(IBlockState state);
	
}
