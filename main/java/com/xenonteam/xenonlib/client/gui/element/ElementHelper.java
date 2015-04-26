/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.element;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class ElementHelper
{

	public static void draw(IGuiElement elm, GuiContainer container)
	{
		TextureManager renderer = Minecraft.getMinecraft().getTextureManager();
		
		renderer.bindTexture(elm.getResource());
		
		container.drawTexturedModalRect(elm.getXPos(), elm.getYPos(), 0, 0, elm.getHeight(), elm.getWidth());
		
	}
	
	public static void drawSprite(IGuiElement elm, int x, int y, GuiContainer container)
	{
		TextureManager renderer = Minecraft.getMinecraft().getTextureManager();
		
		renderer.bindTexture(elm.getResource());
		
		container.drawTexturedModalRect(elm.getXPos(), elm.getYPos(), x, y, elm.getHeight(), elm.getWidth());
	}
	
	public static void unloadResourceLocation(ResourceLocation loc)
	{
		Minecraft.getMinecraft().renderEngine.deleteTexture(loc);
	}
	
}
