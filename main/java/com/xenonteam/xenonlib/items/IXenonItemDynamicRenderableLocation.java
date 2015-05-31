/**
 * 
 */
package com.xenonteam.xenonlib.items;

import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * @author tim4242
 *
 */
public interface IXenonItemDynamicRenderableLocation
{

	public ResourceLocation getTextureLocation(IBakedModel model, int color, ItemStack stack);
	
}
