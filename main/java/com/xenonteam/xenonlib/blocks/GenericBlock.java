/**
 * 
 */
package com.xenonteam.xenonlib.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public class GenericBlock extends BlockContainer {

	
	
	protected GenericBlock(Material material, String UnlocalizedName) {
		super(material);
		this.setUnlocalizedName(UnlocalizedName);
		this.setHardness(1.0F);
		this.setResistance(1.0F);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return null;
	}

}
