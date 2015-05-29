package com.xenonteam.xenonlib.client.gui;

import java.lang.reflect.Constructor;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import com.xenonteam.xenonlib.items.ItemXenonInfoBook;
import com.xenonteam.xenonlib.server.inventory.container.TestContainer;
import com.xenonteam.xenonlib.tileentity.TETest;

public class GuiHandler implements IGuiHandler
{

	public static enum guiIds
	{
		GUI_TEST, GUI_BOOK_INFO;

	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch(guiIds.values()[ID])
		{
			case GUI_TEST:
				return new GuiTest(player.inventory, (TETest) world.getTileEntity(new BlockPos(x, y, z)));

			case GUI_BOOK_INFO:
				boolean ownGui = false;
				GuiScreen otherGui = null;
				ItemStack stack = player.inventory.mainInventory[player.inventory.currentItem];

				if(stack == null || !(stack.getItem() instanceof ItemXenonInfoBook))
				{
					return null;
				} else
				{

					ItemXenonInfoBook book = (ItemXenonInfoBook) stack.getItem();
					
					String modid = book.getModID();

					if(modid == null)
						return null;

					if(book.getGui() != null)
					{
						otherGui =  book.getGui();
					}
					else
					{
						ownGui = true;
					}

					if(ownGui || otherGui == null)
						return new GuiInfoBook(player, modid);
					else
						return otherGui;
				}

			default:
				return null;
		}
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{

		switch(guiIds.values()[ID])
		{
			case GUI_TEST:
				return new TestContainer(player.inventory, (TETest) world.getTileEntity(new BlockPos(x, y, z)));

			default:
				return null;

		}
	}
}
