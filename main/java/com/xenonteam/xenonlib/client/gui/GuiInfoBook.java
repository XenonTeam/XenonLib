/**
 * 
 */
package com.xenonteam.xenonlib.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;

import com.xenonteam.xenonlib.api.book.InfoBookContent;
import com.xenonteam.xenonlib.client.gui.element.GuiElementButton;
import com.xenonteam.xenonlib.client.gui.element.GuiElementLable;
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
	private InfoBookContent m_content;

	/**
	 * @param container
	 * @param tile
	 */
	public GuiInfoBook(EntityPlayer player, String modid, InfoBookContent cont)
	{
		m_modid = modid;
		m_player = player;
		m_content = cont;
		
		generate(null);
	}

	@Override
	public void generate(Object obj)
	{
		
		GuiElementLable title = new GuiElementLable(this, StatCollector.translateToLocal(m_content.getTitle()));
		title.setShouldRenderBackground(true);
		title.setPos(this.width / 2 - title.getWidth() / 2, 10);
		
		
		
		this.addGuiElement("lable_title", title);
	}

}
