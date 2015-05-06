/**
 * 
 */
package com.xenonteam.xenonlib.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;

import com.xenonteam.xenonlib.client.gui.GuiHandler.guiIds;
import com.xenonteam.xenonlib.main.XenonLib;
import com.xenonteam.xenonlib.tileentity.TETest;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public class BlockTest extends BlockContainer
{

	public BlockTest()
	{
		super(Material.ground);
		this.setUnlocalizedName("test");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
	{

		if (!player.isSneaking())
		{
			FMLNetworkHandler.openGui(player, XenonLib.INSTANCE, guiIds.GUI_TEST.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
		}

		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TETest();
	}

}
