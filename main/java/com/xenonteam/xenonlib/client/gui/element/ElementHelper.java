/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.element;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

import com.xenonteam.xenonlib.client.render.SpriteSheet;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class ElementHelper
{

	public static final int COLOR_WHITE = 0xFFFFFF;
	public static final int COLOR_DBLUE = 0x0000AA;
	public static final int COLOR_DAQUA = 0x00AAAA;
	public static final int COLOR_DGREEN = 0x00AA00;
	public static final int COLOR_DRED = 0xAA0000;
	public static final int COLOR_BLACK = 0x000000;
	public static final int COLOR_DPURPLE = 0xAA00AA;
	public static final int COLOR_DGRAY = 0x555555;
	public static final int COLOR_GOLD = 0xFFAA00;
	public static final int COLOR_GRAY = 0xAAAAAA;
	public static final int COLOR_BLUE = 0x5555FF;
	public static final int COLOR_GREEN = 0x55FF55;
	public static final int COLOR_RED = 0xFF5555;
	
	public static void draw(IGuiElement.IGuiSpriteHandler elm, GuiContainer container)
	{
		TextureManager renderer = Minecraft.getMinecraft().getTextureManager();
		
		renderer.bindTexture(SpriteSheet.getSpriteSheet(elm.getSpriteSheet()).getResource());
		
		container.drawTexturedModalRect(elm.getXOff() + elm.getParent().getXOff(), elm.getYOff() + elm.getParent().getYOff(), 0, 0, elm.getHeight(), elm.getWidth());
		
	}
	
	public static void drawSprite(IGuiElement.IGuiSpriteHandler elm, int x, int y, GuiContainer container)
	{
		TextureManager renderer = Minecraft.getMinecraft().getTextureManager();
		
		renderer.bindTexture(SpriteSheet.getSpriteSheet(elm.getSpriteSheet()).getResource());
		
		container.drawTexturedModalRect(elm.getXOff() + elm.getParent().getXOff(), elm.getYOff() + elm.getParent().getYOff(), x, y, elm.getHeight(), elm.getWidth());
	}
	
	public static void drawString(IGuiElement elm, int x, int y, GuiContainer container, String text, int color)
	{
		container.drawString(Minecraft.getMinecraft().fontRendererObj, text, elm.getXOff() + elm.getParent().getXOff(), elm.getYOff() + elm.getParent().getYOff(), color);
	}
	
	public static void unloadResourceLocation(ResourceLocation loc)
	{
		Minecraft.getMinecraft().renderEngine.deleteTexture(loc);
	}
	
}
