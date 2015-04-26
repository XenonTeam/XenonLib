/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.element;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;
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
		
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0); glVertex2f(0, 0);
		glTexCoord2f(0, 1); glVertex2f(0, elm.getYSize());
		glTexCoord2f(1, 1); glVertex2f(elm.getXSize(), elm.getYSize());
		glTexCoord2f(1, 0); glVertex2f(elm.getXSize(), 0);
		glEnd();
		
		
	}
	
}
