/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.element;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class SpriteSheet
{

	public static class Sprite
	{
		protected int m_x;
		protected int m_y;
		protected int m_w;
		protected int m_h;
		
		public Sprite(int x, int y, int w, int h)
		{
			m_x = x;
			m_y = y;
			m_w = w;
			m_h = h;
		}
		
		public int getX()
		{
			return m_x;
		}
		
		public int getY()
		{
			return m_y;
		}
		
		public int getWidth()
		{
			return m_w;
		}
		
		public int getHeight()
		{
			return m_h;
		}
	}
	
	protected ResourceLocation m_loc;
	
	protected Map<String, Sprite> m_sprites;
	
	public SpriteSheet(ResourceLocation loc)
	{
		m_loc = loc;
		m_sprites = new HashMap<String, Sprite>();
	}
	
	public void addSprite(String id, Sprite s)
	{
		if(!m_sprites.containsKey(id))
		m_sprites.put(id, s);
		else
		return;
	}
	
	public void addSprite(String id, int x, int y, int w, int h)
	{
		addSprite(id, new Sprite(x, y, w, h));
	}
	
	public Sprite getSprite(String id)
	{
		if(m_sprites.containsKey(id))
		return m_sprites.get(id);
		else
		return null;
	}
	
	public ResourceLocation getResource()
	{
		return m_loc;
	}
	
	public void drawSprite(IGuiElement elm, Sprite s, GuiContainer container)
	{
		TextureManager renderer = Minecraft.getMinecraft().getTextureManager();
		
		renderer.bindTexture(m_loc);
		
		container.drawTexturedModalRect(elm.getXPos(), elm.getYPos(), s.m_x, s.m_y, elm.getXSize(), elm.getYSize());
	}
	
}
