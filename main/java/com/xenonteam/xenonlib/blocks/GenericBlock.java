/**
 * 
 */
package com.xenonteam.xenonlib.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public class GenericBlock extends Block {

	/**
	 * @param materialIn
	 */
	protected GenericBlock(Material material, String UnlocalizedName) {
		super(material);
		this.setUnlocalizedName(UnlocalizedName);
		this.setHardness(1.0F);
		this.setResistance(1.0F);
	}

}
