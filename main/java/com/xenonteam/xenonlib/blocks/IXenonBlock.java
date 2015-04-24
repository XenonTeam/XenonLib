/**
 * 
 */
package com.xenonteam.xenonlib.blocks;

import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;

/**
 * @author tim4242
 * @author philipas
 *
 */
public interface IXenonBlock
{

	public void setTileEntity(Class<?extends TileEntity> tile);
	public void setItemBlock(Item item);
	
}
