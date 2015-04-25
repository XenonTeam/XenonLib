/**
 * 
 */
package com.xenonteam.xenonlib.blocks;

import java.awt.image.TileObserver;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public class GenericBlock extends Block implements ITileEntityProvider {

	/**
	 * @param materialIn
	 */
	protected GenericBlock(Material material, String UnlocalizedName) {
		super(material);
		this.setUnlocalizedName(UnlocalizedName);
		this.setHardness(1.0F);
		this.setResistance(1.0F);
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.ITileEntityProvider#createNewTileEntity(net.minecraft.world.World, int)
	 */
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return null;
	}

}
