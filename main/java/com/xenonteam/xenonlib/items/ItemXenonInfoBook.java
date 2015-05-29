/**
 * 
 */
package com.xenonteam.xenonlib.items;

import com.xenonteam.xenonlib.client.gui.GuiHandler;
import com.xenonteam.xenonlib.main.XenonLib;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author tim4242
 *
 */
public class ItemXenonInfoBook extends Item implements IXenonItem
{

	protected final String m_modid;
	protected final Class<? extends GuiScreen> m_screen;
	
	public ItemXenonInfoBook(String modid, String name, Class<? extends GuiScreen> screen, CreativeTabs tab)
	{
		this.setCreativeTab(tab);
		this.setUnlocalizedName(modid + "_" + name);
		
		m_modid = modid;
		m_screen = screen;
		
		GameRegistry.registerItem(this, modid + "_" + name);
	}
	
	public final String getModID()
	{
		return m_modid;
	}
	
	public GuiScreen getGui()
	{
		if(m_screen == null)
			return null;
		
		try
		{
			return m_screen.newInstance();
		} catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		player.openGui(XenonLib.INSTANCE, GuiHandler.guiIds.GUI_BOOK_INFO.ordinal(), world, player.serverPosX, player.serverPosY, player.serverPosZ);
		
		return stack;
	}
	
}
