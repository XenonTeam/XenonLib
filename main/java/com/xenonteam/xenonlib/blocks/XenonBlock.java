/**
 * 
 */
package com.xenonteam.xenonlib.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class XenonBlock extends Block implements IXenonBlock, ITileEntityProvider
{

	private Class<? extends TileEntity> m_tile;
	private Item m_itemBlock;

	/**
	 * @param materialIn
	 */
	protected XenonBlock()
	{
		super(Material.glass);
		
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.blocks.IXenonBlock#setTileEntity(java.lang.Class)
	 */
	@Override
	public void setTileEntity(Class<? extends TileEntity> tile)
	{
		m_tile = tile;
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.blocks.IXenonBlock#setItemBlock(net.minecraft.item.Item)
	 */
	@Override
	public void setItemBlock(Item item)
	{
		m_itemBlock = item;
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.ITileEntityProvider#createNewTileEntity(net.minecraft.world.World, int)
	 */
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		try
		{
			return m_tile.newInstance();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
