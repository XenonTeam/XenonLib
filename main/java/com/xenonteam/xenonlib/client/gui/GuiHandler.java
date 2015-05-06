package com.xenonteam.xenonlib.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import com.xenonteam.xenonlib.server.inventory.container.TestContainer;
import com.xenonteam.xenonlib.tileentity.TETest;

public class GuiHandler implements IGuiHandler
{

	public static enum guiIds
	{
		GUI_TEST;

	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (guiIds.values()[ID])
		{
		case GUI_TEST:
			return new GuiTest(player.inventory, (TETest) world.getTileEntity(new BlockPos(x, y, z)));
		}

		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{

		switch (guiIds.values()[ID])
		{
		case GUI_TEST:
			return new TestContainer(player.inventory, (TETest) world.getTileEntity(new BlockPos(x, y, z)));

		}
		return null;
	}
}
