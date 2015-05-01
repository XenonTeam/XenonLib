/**
 * 
 */
package com.xenonteam.xenonlib.blocks;

import net.minecraft.tileentity.TileEntity;

/**
 * @author tim4242
 * @author philipas
 *
 */
public interface IXenonTEP
{

	/**
	 * 
	 * Sets the {@link net.minecraft.tileentity.TileEntity TileEntity} to be used.
	 * 
	 * @param tile The class of the {@link net.minecraft.tileentity.TileEntity TileEntity}
	 */
	public void setTileEntity(Class<?extends TileEntity> tile);
	
}
