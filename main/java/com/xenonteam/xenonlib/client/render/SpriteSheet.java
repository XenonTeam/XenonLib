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

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

import com.xenonteam.xenonlib.client.gui.element.IGuiElement;
import com.xenonteam.xenonlib.util.Log;
import com.xenonteam.xenonlib.util.XUtils;
import com.xenonteam.xenonlib.util.java.FileHelper;

/**
 * This class stores all the information needed to draw images to a gui
 * 
 * @author tim4242
 * @author philipas
 *
 */
public class SpriteSheet
{
	private int m_h, m_w;

	protected static HashMap<String, SpriteSheet> spritesheets = new HashMap<String, SpriteSheet>();

	/**
	 * 
	 * This class contains the information about the coordinates of a sprite on a {@link SpriteSheet}
	 * 
	 * @author tim4242
	 * @author philipas
	 *
	 */
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

	/**
	 * 
	 * This stores the {@link net.minecraft.util.ResourceLocation ResourceLocation} that this spritesheet represenets
	 * 
	 */
	protected ResourceLocation m_loc;
	
	/**
	 * This Map stored all the {@link SpriteSheet.Sprite Sprites} in this Spritesheet
	 */
	protected Map<String, Sprite> m_sprites;

	/**
	 * The default constructor to this class, you can either store a {@link SpriteSheet} you're self or use the method of getting the spritesheet from the internal registry with the id you provide.
	 * 
	 * @param MapId The id of this spritesheet 
	 * @param loc The {@link net.minecraft.util.ResourceLocation ResourceLocation} this spritesheet should represent.
	 */
	private SpriteSheet(String MapId, ResourceLocation loc)
	{

		if (!spritesheets.containsKey(MapId))
		{
			int[] size = XUtils.getSpriteSheetSize(loc);

			m_loc = loc;
			this.m_h = size[1];
			this.m_w = size[0];
			m_sprites = new HashMap<String, Sprite>();

			loadSpritesByFile();
			spritesheets.put(MapId, this);
		} else
		{
			Log.error("There is allready a spritesheet that has the ID : " + MapId + " :");
		}
	}
	
	public static SpriteSheet getSpriteSheet(String id)
	{
		return spritesheets.get(id);
	}
	
	public static void addSpriteSheet(String mapID, ResourceLocation loc)
	{
		new SpriteSheet(mapID, loc);
	}
	
	/**
	 * Adds a {@link Sprite} to this spritesheet
	 * 
	 * @param id The id
	 * @param s The {@link Sprite}
	 */
	public void addSprite(String id, Sprite s)
	{
		if (!m_sprites.containsKey(id))
		{
			Log.debug("Added sprite " + id);
			m_sprites.put(id, s);
		}
		else
			return;
	}
	
	/**
	 * Adds a {@link Sprite} to this spritesheet
	 * 
	 * @param id The id
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @param w The width
	 * @param h The height
	 */
	public void addSprite(String id, int x, int y, int w, int h)
	{
		addSprite(id, new Sprite(x, y, w, h));
	}

	/**
	 * Gets a {@link Sprite} by it's id
	 * 
	 * @param id The id
	 * @return A {@link Sprite}
	 */
	public Sprite getSprite(String id)
	{
		if (m_sprites.containsKey(id))
			return m_sprites.get(id);
		else
			return null;
	}

	/**
	 * @return This spritesheets {@link net.minecraft.util.ResourceLocation ResourceLocation}
	 */
	public ResourceLocation getResource()
	{
		return m_loc;
	}

	/**
	 * Draws a sprite to the provided {@link net.minecraft.client.gui.inventory.GuiContainer GuiContainer} 
	 * 
	 * @param elm The {@link com.xenonteam.xenonlib.client.gui.element.IGuiElement IGuiElement} to be drawn
	 * @param id The id of the {@link Sprite} to be drawn
	 * @param container The {@link net.minecraft.client.gui.inventory.GuiContainer GuiContainer} to be drawn in
	 */
	public void drawSprite(IGuiElement elm, String id, GuiContainer container)
	{
		Sprite s = m_sprites.get(id);

		container.mc.renderEngine.bindTexture(getResource());

		container.drawScaledCustomSizeModalRect(elm.getXOff() + elm.getParent().getXOff(), elm.getYOff() + elm.getParent().getYOff(), s.m_x, s.m_y, s.m_w, s.m_h, elm.getWidth(), elm.getHeight(), this.m_w, this.m_h);
	}
	
	/**
	 * 
	 * <b><u>This method should only be called from inside this class</u></b>
	 * 
	 * <br>
	 * <br>
	 * 
	 * This method collects all sprites from the .sprites file connected to the spritesheet and puts them into the internal Map {@link SpriteSheet#m_sprites m_sprites}. 
	 * 
	 */
	protected void loadSpritesByFile()
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

			} else if (split[0].startsWith("S"))
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
