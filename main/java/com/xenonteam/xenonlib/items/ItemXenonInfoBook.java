/**
 * 
 */
package com.xenonteam.xenonlib.items;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author tim4242
 *
 */
public class ItemXenonInfoBook extends Item implements IXenonItem
{

	protected final String m_modid;
	protected final GuiScreen m_screen;
	
	public ItemXenonInfoBook(String modid, String name, GuiScreen screen, CreativeTabs tab)
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
		return m_screen;
	}
	
}
