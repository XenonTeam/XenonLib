/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.element;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;

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
		
		container.drawTexturedModalRect(elm.getXPos(), elm.getYPos(), 0, 0, elm.getXSize(), elm.getYSize());
		
	}
	
	public static void drawSprite(IGuiElement elm, int x, int y, GuiContainer container)
	{
		TextureManager renderer = Minecraft.getMinecraft().getTextureManager();
		
		renderer.bindTexture(elm.getResource());
		
		container.drawTexturedModalRect(elm.getXPos(), elm.getYPos(), x, y, elm.getXSize(), elm.getYSize());
	}
	
}
