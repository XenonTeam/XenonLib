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
public interface IXenonBlockDynamicRenderableLocation
{

	public ResourceLocation getTextureLocation(IBlockState state);
	
}
