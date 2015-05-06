/**
 * 
 */
package com.xenonteam.xenonlib.client.render;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

import com.xenonteam.xenonlib.client.gui.element.IGuiElement;
import com.xenonteam.xenonlib.util.XUtils;
import com.xenonteam.xenonlib.util.java.FileHelper;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class SpriteSheet
{
	private int m_h, m_w;

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

	public Map<String, Sprite> m_sprites;

	public SpriteSheet(ResourceLocation loc, int hight, int with)
	{
		m_loc = loc;
		this.m_h = hight;
		this.m_w = with;
		m_sprites = new HashMap<String, Sprite>();

		loadSpritesByFile();
	}

	public SpriteSheet(ResourceLocation loc)
	{
		int[] size = XUtils.getSpriteSheetSize(loc);

		m_loc = loc;
		this.m_h = size[1];
		this.m_w = size[0];
		m_sprites = new HashMap<String, Sprite>();

		loadSpritesByFile();
	}

	public void addSprite(String id, Sprite s)
	{
		if (!m_sprites.containsKey(id))
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
		if (m_sprites.containsKey(id))
			return m_sprites.get(id);
		else
			return null;
	}

	public ResourceLocation getResource()
	{
		return m_loc;
	}

	public void drawSprite(IGuiElement elm, String id, GuiContainer container)
	{
		Sprite s = m_sprites.get(id);

		TextureManager renderer = Minecraft.getMinecraft().getTextureManager();

		renderer.bindTexture(m_loc);

		container.drawTexturedModalRect(elm.getXOff() + elm.getParent().getXOff(), elm.getYOff() + elm.getYOff(), s.m_x, s.m_y, elm.getHeight(), elm.getWidth());
	}

	private void loadSpritesByFile()
	{
		if (XUtils.getStreamFromRL(new ResourceLocation(m_loc.getResourceDomain(), m_loc.getResourcePath() + ".sprites")) == null)
		{
			return;
		}


		
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(XUtils.getStreamFromRL(new ResourceLocation(m_loc.getResourceDomain(), m_loc.getResourcePath() + ".sprites"))));

		ArrayList<String> lines = new ArrayList<String>();

		try
		{
			lines.addAll(FileHelper.readFile(reader));
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		String metaline = lines.get(0);
		
		String version = metaline;

		lines.remove(0);

		
		
		for (String line : lines)
		{

			
			String id = null;
			int x = 0;
			int y = 0;
			int w = 0;
			int h = 0;

			String[] split = line.split(":");

			if (split[0].startsWith("C"))
			{
				
				String[] splitI = split[1].split(",");
				
				if (splitI.length != 5)
				{
					continue;
				}

				for (String s : splitI)
				{
					String s1 = s.replaceAll("[\\s]", "");

					String[] sarr = s1.split("=");
					
					if (sarr[0].equals("id"))
					{
						id = sarr[1];
					} else if (sarr[0].equals("x"))
					{
						x = Integer.parseInt(sarr[1]);
					} else if (sarr[0].equals("y"))
					{
						y = Integer.parseInt(sarr[1]);
					} else if (sarr[0].equals("w"))
					{
						w = Integer.parseInt(sarr[1]);
					} else if (sarr[0].equals("h"))
					{
						h = Integer.parseInt(sarr[1]);
					}
				}

			} else if(split[0].startsWith("S"))
			{
				
				String[] splitI = split[1].split(",");
				
				if (splitI.length != 5)
				{
					continue;
				}

				id = splitI[0];
				x = Integer.parseInt(splitI[1]);
				y = Integer.parseInt(splitI[2]);
				w = Integer.parseInt(splitI[3]);
				h = Integer.parseInt(splitI[4]);
				
				
			}
			
			addSprite(id, x, y, w, h);

		}
		

	}

	/**
	 * Do not use this unless your sprites are the same size
	 * 
	 * @param baseID
	 *            The base id you want to use
	 * @param hight
	 *            The height of the sprite of the sprites
	 * @param with
	 *            The with of the sprite of the sprites
	 */
	public void addAllSprites(String baseID, int hight, int with)
	{
		int x_size = m_w / with;
		int y_size = m_w / hight;

		for (int i = 0; i < x_size; i++)
		{
			for (int j = 0; j < y_size; j++)
			{
				Sprite temp = new Sprite(0 + with * i, 0 + hight * j, with, hight);
				this.addSprite(baseID + "_" + i, temp);
			}
		}

	}

}
