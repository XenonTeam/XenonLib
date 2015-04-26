/**
 * 
 */
package com.xenonteam.xenonlib.blocks;

import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;

/**
 * @author tim4242
 * @author philipas
 * 
 * <br>
 * <br>
 * 
 * This is a Interface that should be used <br> on every 
 * {@link net.minecraft.block.Block Block} <br> that is created using this library. 
 */
public interface IXenonBlock
{

	/**
	 * 
	 * Sets the {@link net.minecraft.tileentity.TileEntity TileEntity} to be used.
	 * 
	 * @param tile The class of the {@link net.minecraft.tileentity.TileEntity TileEntity}
	 */
	public void setTileEntity(Class<?extends TileEntity> tile);
	
	/**
	 * Sets the {@link net.minecraft.item.ItemBlock ItemBlock} to be used
	 * 
	 * @param item The {@link net.minecraft.item.ItemBlock ItemBlock}
	 */
	public void setItemBlock(ItemBlock item);
	
}
