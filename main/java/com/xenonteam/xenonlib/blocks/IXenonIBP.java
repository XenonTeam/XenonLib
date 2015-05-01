/**
 * 
 */
package com.xenonteam.xenonlib.blocks;

import net.minecraft.item.ItemBlock;

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
public interface IXenonIBP
{

	
	
	/**
	 * Sets the {@link net.minecraft.item.ItemBlock ItemBlock} to be used
	 * 
	 * @param item The {@link net.minecraft.item.ItemBlock ItemBlock}
	 */
	public void setItemBlock(ItemBlock item);
	
}
