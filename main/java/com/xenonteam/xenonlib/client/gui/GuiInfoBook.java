/**
 * 
 */
package com.xenonteam.xenonlib.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;

import com.xenonteam.xenonlib.client.gui.factory.GuiFactoryContainer;
import com.xenonteam.xenonlib.client.gui.factory.GuiFactoryScreen;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class GuiInfoBook extends GuiFactoryScreen
{

	private String m_modid;
	private EntityPlayer m_player;

	/**
	 * @param container
	 * @param tile
	 */
	public GuiInfoBook(EntityPlayer player, String modid)
	{
		m_modid = modid;
		m_player = player;
	}

	@Override
	public void generate(Object obj)
	{

	}

}
