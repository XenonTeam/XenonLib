/**
 * 
 */
package com.xenonteam.xenonlib.api.book;

import java.lang.reflect.InvocationTargetException;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.xenonteam.xenonlib.api.XenonAPI;
import com.xenonteam.xenonlib.client.render.SpriteSheet.Sprite;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class InfoBookContent
{

	private Item m_book = null;

	private String m_title;
	private String m_spriteSheet;
	private String m_titleImg;

	public InfoBookContent(String modid, String unlocName, Class<? extends GuiScreen> screen, CreativeTabs tab, String title, String sheet, String titleImg)
	{
		XenonAPI.initAPI();

		try
		{
			if(screen == null)
			{
				m_book = (Item) Class.forName("com.xenonteam.xenonlib.items.ItemXenonInfoBook").getConstructor(String.class, String.class, CreativeTabs.class).newInstance(modid, unlocName, tab);
			} else
			{
				m_book = (Item) Class.forName("com.xenonteam.xenonlib.items.ItemXenonInfoBook").getConstructor(String.class, String.class, Class.class, CreativeTabs.class).newInstance(modid, unlocName, screen, tab);
			}
		} catch(Exception e)
		{
			e.printStackTrace();
		}

		m_title = title;
		m_spriteSheet = sheet;
		m_titleImg = titleImg;
	}

	public InfoBookContent(String modid, String unlocName, CreativeTabs tab, String title, String sheet, String titleImg)
	{
		this(modid, unlocName, null, tab, title, sheet, titleImg);
	}

	public Item getBookItem()
	{
		return m_book;
	}

	public String getTitle()
	{
		return m_title;
	}

	public String getSpriteSheedID()
	{
		return m_spriteSheet;
	}

	public String getTitleSpriteIF()
	{
		return m_titleImg;
	}

}
