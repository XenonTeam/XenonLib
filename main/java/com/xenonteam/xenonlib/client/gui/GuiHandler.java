package com.xenonteam.xenonlib.client.gui;

import java.lang.reflect.Constructor;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

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
				NBTTagCompound stackInfo = player.inventory.mainInventory[player.inventory.currentItem].getTagCompound();

				if(stackInfo == null)
				{
					return null;
				} else
				{

					String modid = stackInfo.getString("mod");

					if(modid == null)
						return null;

					if(stackInfo.getString("gui") != null)
					{

						try
						{
							Constructor[] guiConstructs = Class.forName(stackInfo.getString("gui")).getConstructors();

							Constructor guiConstruct = null;
							Object[] args = null;

							for (Constructor c : guiConstructs)
							{
								Class<?>[] ca = c.getParameterTypes();

								if(ca == null || (ca[0] == null && ca.length == 1))
								{
									guiConstruct = c;
									args = new Object[] {};
									break;
								} else if(ca[0] == EntityPlayer.class && ca.length == 1)
								{
									guiConstruct = c;
									args = new Object[] { player };
									break;
								} else if(ca[0] == World.class && ca.length == 1)
								{
									guiConstruct = c;
									args = new Object[] { world };
									break;
								} else if(ca[0] == EntityPlayer.class && ca[1] == World.class && ca.length == 2)
								{
									guiConstruct = c;
									args = new Object[] { player, world };
									break;
								}
							}

							Object o = guiConstruct.newInstance(args);

							if(o instanceof GuiScreen)
							{
								otherGui = (GuiScreen) o;
							}
						} catch(Exception e)
						{
							ownGui = true;
						}
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
