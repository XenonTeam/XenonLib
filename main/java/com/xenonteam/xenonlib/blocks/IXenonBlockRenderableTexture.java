/**
 * 
 */
package com.xenonteam.xenonlib.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

/**
 * @author tim4242
 *
 */
public interface IXenonBlockRenderableTexture
{

	public TextureAtlasSprite getTexture(IBlockState state);
	
}
